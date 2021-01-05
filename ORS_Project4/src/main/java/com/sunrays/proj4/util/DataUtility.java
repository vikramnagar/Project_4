package com.sunrays.proj4.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sunrays.proj4.util.DataValidator;

/**
 * Data Utility class to format data from one format to another
 * 
* @author Interpreter
 * @version 1.0
 */

public class DataUtility {

    /**
     * Application Date Format
     */
    public static final String APP_DATE_FORMAT = "MM/dd/yyyy";

    public static final String APP_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /**
     * Date formatter
     */
    private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);

    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

    /**
     * Trims and trailing and leading spaces of a String
     * 
     * @param val
     * @return String
     */
    public static String getString(String val) {
        if (DataValidator.isNotNull(val)) {
            return val.trim();
        } else {
            return val;
        }
    }

    /**
     * Converts Object to String
     * 
     * @param val
     * @return String
     */
    public static String getStringData(Object val) {
        if (val != null) {
            return val.toString();
        } else {
            return "";
        }
    }

    /**
     * Converts String to Integer
     * 
     * @param val
     * @return int
     */
    public static int getInt(String val) {
        if (DataValidator.isInteger(val)) {
            return Integer.parseInt(val);
        } else {
            return 0;
        }
    }

    /**
     * Converts String into Long
     * 
     * @param val
     * @return long
     */
    public static long getLong(String val) {
        if (DataValidator.isLong(val)) {
            return Long.parseLong(val);
        } else {
            return 0;
        }
    }

    /**
     * Converts String into Date
     * 
     * @param val
     * @return Date
     */
    public static Date getDate(String val) {
        Date date = null;
        try {
            date = formatter.parse(val);
        } catch (Exception e) {

        }
        return date;
    }	

    /**
     * Converts Date into String
     * 
     * @param date
     * @return String
     */
    public static String getDateString(Date date) {
        try {
            return formatter.format(date);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * Gets date after n number of days
     * 
     * @param date
     * @param day
     * @return Date
     */
    public static Date getDate(Date date, int day) {
        return null;
    }

    /**
     * Converts String into Time
     * 
     * @param val
     * @return Timestamp
     */
    public static Timestamp getTimestamp(String val) {

        Timestamp timeStamp = null;
        try {
            timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
        } catch (Exception e) {
            return null;
        }
        return timeStamp;
    }
    /**
     * Converts long into Time
     * 
     * @param long
     * @return Timestamp
     */
    public static Timestamp getTimestamp(long l) {

        Timestamp timeStamp = null;
        try {
            timeStamp = new Timestamp(l);
        } catch (Exception e) {
            return null;
        }
        return timeStamp;
    }
    /**
     * Converts null into timeStamp
     * 
     * @param null
     * @return Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        Timestamp timeStamp = null;
        try {
            timeStamp = new Timestamp(new Date().getTime());
        } catch (Exception e) {
        }
        return timeStamp;

    }
    /**
     * Converts Timestamp into Time
     * 
     * @param Timestamp
     * @return long
     */
    public static long getTimestamp(Timestamp tm) {
        try {
            return tm.getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Test above methods
     * 
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        System.out.println(getInt("124"));
    }

}
