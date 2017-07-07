package com.mobinteg.utilslib;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.text.InputType;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

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

    public static String convertDate(String originalDate, String originalFormat, String targetFormat, Locale originLocale, Locale targetLocale, boolean... error) {

        SimpleDateFormat sourceFormat = null, desiredFormat = null;
        Date date = null;

        try {
            sourceFormat = new SimpleDateFormat(originalFormat, originLocale);
            desiredFormat = new SimpleDateFormat(targetFormat, targetLocale);

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

    /**
     *
     * @param dims
     * @return Returns an image with size 600*600 if no dimensions parameters are specified
     */

    public static String image(int... dims){

        int width = 600;
        int height = 600;

        Random r = new Random();
        int n = r.nextInt(1000);

        if(dims!=null && dims.length==2){
            width = dims[0];
            height = dims[1];
        }
        return "https://unsplash.it/" + width + "/" + height + "?image=" + n;
    }


    public static void ud(Context mContext, String... str) {
        String text = "Feature under development";
        if(str!=null){
            text= str[0];
        }
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public static String currentDate(String s) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(s, Locale.US);
        return df.format(c.getTime());
    }

    public static String randomId() {
        return UUID.randomUUID().toString();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isEmptyField(String field) {
        return field.length()>0;
    }

    /**
     *Makes the edittext view focused and capitalized if the last parameter is true
     *
     * @param context
     * @param view
     * @param isText
     *
     */
    public static void focus(Context context, EditText view, boolean... isText) {
        view.requestFocus();
        if (isText != null && isText[0]){
            view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    public static String unixToDate(long timestamp, String s) {

        String date = DateFormat.format(s, timestamp).toString();
        return date;
    }
}
