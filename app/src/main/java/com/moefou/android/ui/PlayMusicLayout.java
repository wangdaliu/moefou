package com.moefou.android.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moefou.android.R;
import com.moefou.android.ui.views.AlphaForegroundColorSpan;
import com.moefou.android.ui.views.font.TypefaceTextView;

public class PlayMusicLayout extends RelativeLayout {

    private static final String TAG = "PlayMusicLayout";
    private ImageView mActionFavImage, mActionDeleteImage, mActionNextImage;
    private ViewGroup mPlayLayout, mActionLayout;
    private View mActionBottomView, mContentBottomView, mContent;
    private ImageView icon;
    private TypefaceTextView mTitleView;

    private RectF mRect1 = new RectF();
    private RectF mRect2 = new RectF();

    private RectF mRect = new RectF();
    private AccelerateDecelerateInterpolator mSmoothInterpolator;

    private int mScreenHeight;
    private int mMinHeaderTranslation;
    private AlphaForegroundColorSpan mAlphaForegroundColorSpan;
    private SpannableString mSpannableString;
    private int mTitleColor;
    private float mTouchY = 0;
    private float mInterpolation = 0;

    public PlayMusicLayout(Context context) {
        this(context, null);
    }

    public PlayMusicLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayMusicLayout(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.play_music_layout, this);

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();

        mActionFavImage = (ImageView) findViewById(R.id.action_unfav);
        mActionDeleteImage = (ImageView) findViewById(R.id.action_delete);
        mActionNextImage = (ImageView) findViewById(R.id.action_next);

        mContent = findViewById(R.id.content);
        mPlayLayout = (ViewGroup) findViewById(R.id.play_layout);

        mActionLayout = (ViewGroup) findViewById(R.id.action_layout);
        mActionBottomView = findViewById(R.id.action_bottom);

        mContentBottomView = findViewById(R.id.bottom_content);

        icon = (ImageView) findViewById(R.id.icon);

        mTitleView = (TypefaceTextView) findViewById(R.id.title);

        mActionFavImage.setEnabled(false);
        mActionDeleteImage.setEnabled(false);
        mActionNextImage.setEnabled(false);

        mTitleColor = getResources().getColor(R.color.green);
        mSpannableString = new SpannableString(getResources().getString(R.string.my_private_music));
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(mTitleColor);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // update mActionBottomView height
                int actionBottomHeight = mActionBottomView.getWidth() * mActionLayout.getHeight() / mActionLayout.getWidth();
                mActionBottomView.getLayoutParams().height = actionBottomHeight;

                Rect frame = new Rect();
                ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;

                DisplayMetrics dm = new DisplayMetrics();
                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
                mScreenHeight = dm.widthPixels;
                mScreenHeight = dm.heightPixels;
                mMinHeaderTranslation = mScreenHeight - statusBarHeight - getResources().getDimensionPixelSize(R.dimen.bottom);
            }
        });

        mContent.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mInterpolation == 1) {
                            mTouchY = mMinHeaderTranslation - event.getRawY();
                        } else {
                            mTouchY = event.getRawY();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (event.getRawY() - mTouchY < 0) {
                            return true;
                        }
                        float scrollY = Math.abs(event.getRawY() - mTouchY);
                        float ratio = clamp(Math.min(scrollY, mMinHeaderTranslation) / mMinHeaderTranslation, 0.0f, 1.0f);
                        mInterpolation = mSmoothInterpolator.getInterpolation(ratio);

                        interpolate(mPlayLayout, icon, mInterpolation);
                        interpolate(mActionLayout, mActionBottomView, mInterpolation);
                        interpolate(mContent, mContentBottomView, mInterpolation);
                        interpolate(mTitleView, mInterpolation);
                        setTitleAlpha((1 - clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F)) / 2);

                        break;
                    case MotionEvent.ACTION_UP:
                        if (mContent.getTranslationY() >= mMinHeaderTranslation / 4) {
                            // set to full screen
                            mInterpolation = 1;
                            interpolateToSize(1);
                        } else {
                            // set to bottom screen
                            mInterpolation = 0;
                            interpolateToSize(0);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void interpolateToSize(float size) {
        interpolate(mPlayLayout, icon, size);
        interpolate(mActionLayout, mActionBottomView, size);
        interpolate(mContent, mContentBottomView, size);
        interpolate(mTitleView, size);
    }

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundColorSpan.setAlpha(alpha);
        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTitleView.setText(mSpannableString);
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    private void interpolate(View view, float interpolation) {
        getOnScreenRect(mRect, view);
        float translationY = interpolation * mScreenHeight;
        view.setTranslationY(translationY);
    }

    private void interpolate(View view1, View view2, float interpolation) {
        getOnScreenRect(mRect1, view1);
        getOnScreenRect(mRect2, view2);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (mRect2.left + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.5F * (interpolation * (mRect2.top + mRect2.bottom - mRect1.top - mRect1.bottom));

        view1.setTranslationX(translationX);
        view1.setTranslationY(translationY);
        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);
    }

    private RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }
}
