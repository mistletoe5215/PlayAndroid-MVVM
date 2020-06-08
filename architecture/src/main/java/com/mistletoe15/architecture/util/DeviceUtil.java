package com.mistletoe15.architecture.util;

import android.content.Context;

/**
 * Created by Mistletoe on 2020/6/8
 **/
public class DeviceUtil {
    public static int dip2px(Context context, float floatDipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (floatDipValue * m + 0.5f);
    }
    public static int px2dip(Context context, float floatDipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (floatDipValue / m + 0.5f);
    }
}
