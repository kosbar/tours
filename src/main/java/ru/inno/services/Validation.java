package ru.inno.services;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
    private static final Logger logger = LogManager.getLogger(Validation.class);

    public static long stringParseInMilliseconds(String dateString, String pattern) {//
        long milliseconds;
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        milliseconds = date.getTime();
        return milliseconds;
    }
}
