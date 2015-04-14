package com.moefou.android.ui.play;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moefou.android.R;

public class PlayMusicLayout extends RelativeLayout {

    private static final String TAG = "PlayMusicLayout";
    private ImageView mActionFavImage, mActionDeleteImage, mActionNextImage;
    private ViewGroup mContent, mPlayLayout, mActionLayout;
    private View mActionBottomView;
    private ImageView icon;

    private RectF mRect1 = new RectF();
    private RectF mRect2 = new RectF();
    private AccelerateDecelerateInterpolator mSmoothInterpolator;

    private int mScreenWidth, mScreenHeigh;
    private int mMinHeaderTranslation;

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

        mContent = (ViewGroup) findViewById(R.id.content);
        mPlayLayout = (ViewGroup) findViewById(R.id.play_layout);

        mActionLayout = (ViewGroup) findViewById(R.id.action_layout);
        mActionBottomView = findViewById(R.id.action_bottom);

        icon = (ImageView) findViewById(R.id.icon);


        mActionFavImage.setEnabled(false);
        mActionDeleteImage.setEnabled(false);
        mActionNextImage.setEnabled(false);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // update mActionBottomView height
                int actionBottomHeight = mActionBottomView.getWidth() * mActionLayout.getHeight() / mActionLayout.getWidth();
                mActionBottomView.getLayoutParams().height = actionBottomHeight;

                //
                DisplayMetrics dm = new DisplayMetrics();
                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
                mScreenWidth = dm.widthPixels;
                mScreenHeigh = dm.heightPixels;
                mMinHeaderTranslation = mScreenHeigh - mPlayLayout.getTop();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();

                Log.d(TAG, "y " + y);

                break;
            case MotionEvent.ACTION_MOVE:
                float scrollY = event.getY() - y;
                float ratio = clamp(Math.min(scrollY, mMinHeaderTranslation) / mMinHeaderTranslation, 0.0f, 1.0f);
                float interpolation = mSmoothInterpolator.getInterpolation(ratio);
                interpolate(mPlayLayout, icon, interpolation);

                interpolate(mActionLayout, mActionBottomView, interpolation);

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return true;
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
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
