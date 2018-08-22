package com.rsp.ndvd.viewUtils;

import android.content.Context;
import android.content.res.TypedArray;

import com.rsp.ndvd.R;

import java.util.Calendar;

public class Utils {

    public static String getGreetingsText() {
        String greetingsText = "";
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        cal.set(Calendar.HOUR_OF_DAY, 3);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);

        long noon_start = cal.getTimeInMillis();//3.00

        cal.set(Calendar.HOUR_OF_DAY, 5);
        cal.set(Calendar.MINUTE, 30);

        long noon_end = cal.getTimeInMillis();//5.30
        long now = System.currentTimeMillis();//current time

        if (now > noon_start && now < noon_end) {
            //set your background here
            greetingsText = "Good afternoon";
        } else {
            //set your background here
            greetingsText = "Good Evening";
        }
        return greetingsText;
    }

    public static int[] getCardColors(Context context) {
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.card_colors);
        int[] colors = new int[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            colors[i] = typedArray.getColor(i, 0);
        }
        typedArray.recycle();
        return colors;
    }
}
