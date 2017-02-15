package com.mobinteg.utilslib;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public static String convertDate(String originalDate, String originalFormat, String targetFormat, Locale locale, boolean... error) {

        SimpleDateFormat sourceFormat = null, desiredFormat = null;
        Date date = null;

        try {
            sourceFormat = new SimpleDateFormat(originalFormat, locale);
            desiredFormat = new SimpleDateFormat(targetFormat, locale);

            date = sourceFormat.parse(originalDate);
        } catch (Exception e) {
            e.printStackTrace();
            if(error.length>0 && error[0]) {
                return e.getMessage();
            }else{
                return "";
            }
        }

        return desiredFormat.format(date.getTime());
    }


}
