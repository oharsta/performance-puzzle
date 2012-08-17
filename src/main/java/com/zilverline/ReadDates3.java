package com.zilverline;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Third version (+/- 2 seconds) of ReadDates. Run with JMV options:
 * 
 * -Xprof -Xmx512m
 * 
 * And look for:
 * 
 * Interpreted + native Method
 * 
 * 19.2% 15 + 0 java.util.Calendar.<init>
 * 
 * Compiled + native Method
 * 
 * 12.8% 1 + 9 java.util.GregorianCalendar.<init>
 * 
 * 11.5% 9 + 0 sun.util.calendar.ZoneInfo.getTransitionIndex
 * 
 */
public class ReadDates3 extends ReadDates2 {

  public static void main(String[] args) throws IOException {
    ReadDates3 main = new ReadDates3();
    main.perform(main);
  }


  @Override
  protected void writeFutureDates(List<String> dateStrings) throws IOException {
    List<Calendar> calendars = new ArrayList<Calendar>();
    for (String dateString : dateStrings) {
      Calendar calendar = new GregorianCalendar();
      try {
        calendar.setTime(new Date(Long.parseLong(dateString)));
        calendars.add(calendar);
      } catch (NumberFormatException e) {
        /*
         * We accidently print out the entire List of strings
         * 
         * LOG.info("Invalid date: '" + dateStrings + "'");
         */
      }
    }
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
        GenerateDates.DATES_FUTURE)));
    for (Calendar calendar : calendars) {
      Date now = new Date();
      if (calendar.getTime().after(now)) {
        out.writeBytes(calendar.getTimeInMillis() + "|");
      }
    }
    out.close();
  }

}
