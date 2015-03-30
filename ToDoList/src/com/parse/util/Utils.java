package com.parse.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils
{
    public static String getDateInFormat(Date date_in)
    {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date_formatted = df.format(date_in);
        return date_formatted;
    }
}
