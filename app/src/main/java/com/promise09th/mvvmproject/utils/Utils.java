package com.promise09th.mvvmproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String convertKakaoDateFormat(String date) {
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
            Date parseDate = parseFormat.parse(date);

            SimpleDateFormat sdp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
            return sdp.format(parseDate);
        } catch (ParseException e) {
            return date;
        }
    }
}