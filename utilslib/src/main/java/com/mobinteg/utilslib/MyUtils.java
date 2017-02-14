package com.mobinteg.utilslib;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

    /**
     *
     * @param context
     * @param dp
     * @return returns dimension in px
     */
    public static int dpToPx(Context context, int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

    /**
     *
     * @param context
     * @param px
     * @return returns dimension in dp
     */
    public static int pxTodp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }


    /**
     *
     * @param context
     * @return [width, height] in pixels
     *
     */
    public static int[] getScreenSize(Context context){
        int[] sizes = new int[2];
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        sizes[0] = size.x;
        sizes[1] = size.y;
        return sizes;
    }

    /**
     *
     * @param originalDate
     * @param originalFormat
     * @param targetFormat
     * @return formatted date
     */

    public static String convertDate(String originalDate, String originalFormat, String targetFormat) {

        SimpleDateFormat sourceFormat = new SimpleDateFormat(originalFormat);
        SimpleDateFormat desiredFormat = new SimpleDateFormat(targetFormat);
        Date date = null;

        try {
            date = sourceFormat.parse(originalDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return desiredFormat.format(date.getTime());
    }


}
