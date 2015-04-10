package com.moefou.android.ui.play;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moefou.android.R;

public class PlayMusicLayout extends RelativeLayout {

    private static final String TAG = "PlayMusicLayout";
    private ViewGroup mActionGroup, mContent;
    private ImageView mActionFavImage, mActionDeleteImage, mActionNextImage;

    public PlayMusicLayout(Context context) {
        this(context, null);
    }

    public PlayMusicLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayMusicLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.play_music_layout, this);
        mActionGroup = (ViewGroup) findViewById(R.id.action_layout);
        mActionFavImage = (ImageView) findViewById(R.id.action_unfav);
        mActionDeleteImage = (ImageView) findViewById(R.id.action_delete);
        mActionNextImage = (ImageView) findViewById(R.id.action_next);
        mContent = (ViewGroup) findViewById(R.id.content);

        mActionFavImage.setEnabled(false);
        mActionDeleteImage.setEnabled(false);
        mActionNextImage.setEnabled(false);
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
                float offY = event.getY() - y;

                Log.d(TAG, "event.getY() " + event.getY());
//                mActionGroup.setLayoutParams(new LinearLayout.LayoutParams((int) offY, mActionGroup.getHeight()));

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return true;
    }
}
