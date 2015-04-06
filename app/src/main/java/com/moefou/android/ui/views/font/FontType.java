package com.moefou.android.ui.views.font;

public enum FontType {

    // Be sure to sync this enum with attrs.xml -> CustomTextAppearance
    ROBOTO_REGULAR("fonts/roboto_regular.ttf"),
    ROBOTO_MEDIUM("fonts/roboto_medium.ttf");

    final String assetUrl;

    FontType(String assetUrl) {
        this.assetUrl = assetUrl;
    }

}
