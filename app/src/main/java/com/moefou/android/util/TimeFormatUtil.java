package com.moefou.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUtil {

    public static String formatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }
}
