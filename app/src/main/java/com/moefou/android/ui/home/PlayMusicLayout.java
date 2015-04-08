package com.moefou.android.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.moefou.android.R;

public class PlayMusicLayout extends RelativeLayout {
    public PlayMusicLayout(Context context) {
        this(context, null);
    }

    public PlayMusicLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayMusicLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.play_music_layout, this);

    }
}
