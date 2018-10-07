package com.wanghan.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateThreadLocal {

    private static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue(){
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return THREAD_LOCAL.get().parse(source);
    }
}
