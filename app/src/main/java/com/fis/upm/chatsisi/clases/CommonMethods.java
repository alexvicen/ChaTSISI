package com.fis.upm.chatsisi.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonMethods {
    private static DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("K:mm:ssa");

    public static String getCurrentTime() {
        Date today = Calendar.getInstance().getTime();
        return timeFormat.format(today);
    }
    public static String getCurrentMilis() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getCurrentDate() {
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

}