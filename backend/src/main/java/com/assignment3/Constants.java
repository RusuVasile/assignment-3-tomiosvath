package com.assignment3;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static DateTimeFormatter getDateTimeFormat(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    }

    public static String getFormat(){
        return "yyyy-MM-dd HH";
    }

}
