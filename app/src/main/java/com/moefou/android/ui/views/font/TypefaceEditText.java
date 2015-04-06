package com.moefou.android.ui.views.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class TypefaceEditText extends EditText {

    public TypefaceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeface(context, attrs);
    }

    public TypefaceEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTypeface(context, attrs);
    }

    private void initTypeface(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        Typeface typeface = TypefaceFactory.getInstance().createFrom(context, attrs);
        setTypeface(typeface);
    }
}
