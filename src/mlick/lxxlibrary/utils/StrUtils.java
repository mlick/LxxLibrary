package com.qiyue.utils;

import android.widget.TextView;

/**
 * Created by lxx on 2016/3/28 10:33.
 * String 工具类
 */
public class StrUtils {

    /**
     * 设置TextView的setText
     *
     * @param tv
     * @param string
     */
    public static void setTvStr(TextView tv, String string) {
        if (tv != null && !isEmpty(string)) {
            tv.setText(string);
        }
    }

    public static void setTvStr(TextView tv, int string) {
        tv.setText(string + "");
    }

    /**
     * 判断String是否为空
     *
     * @param var0
     * @return
     */
    public static boolean isEmpty(String var0) {
        return var0 == null || var0.length() <= 0;
    }

    public static boolean isNotEmpty(String var0) {
        return !isEmpty(var0);
    }

}
