package com.ftn.cdss.util;

import java.util.Date;

public class DateUtil {

    public static Date addDate(Date date, String entity, int number) {

        java.util.Calendar newCal = java.util.Calendar.getInstance();
        newCal.setTime(date);

        if (entity.equals("y")) { //years
            newCal.add(java.util.Calendar.YEAR, number);
        } else if (entity.equals("m")) { //months
            newCal.add(java.util.Calendar.MONTH, number);
        } else if (entity.equals("d")) { //days
            newCal.add(java.util.Calendar.DATE, number);
        }
        return newCal.getTime();
    }
}
