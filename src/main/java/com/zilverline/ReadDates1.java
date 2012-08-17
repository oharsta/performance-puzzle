package com.zilverline;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Purpose is to read the src/test/resoruces/dates.txt and write all the dates
 * that are in the future to the src/test/resources/datesFuture.txt in the
 * format ..22323|14390..
 * 
 * 
 * First (slow version +/- 27 seconds) of ReadDates. Run with JMV options:
 * 
 * -Xprof -Xmx512m
 * 
 * And look for:
 * 
 * Stub + native Method
 * 
 * 53.7% 0 + 832 java.io.FileInputStream.read
 * 
 */
public class ReadDates1 {

  static final Logger LOG = Logger.getLogger(ReadDates1.class.getName());
  static {
    LOG.setLevel(Level.WARNING);
  }

  public static void main(String[] args) throws IOException {
    ReadDates1 main = new ReadDates1();
    main.perform(main);
  }

  protected void perform(ReadDates1 main) throws IOException {
    main.deletePrevious();
    String datesString = main.readDates();
    List<String> dateStrings = Arrays.asList(datesString.split("\\|"));
    main.writeFutureDates(dateStrings);
  }

  protected String readDates() throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    InputStream is = new FileInputStream(GenerateDates.DATES);
    int b;
    while ((b = is.read()) != -1) {
      buffer.write(b);
    }
    buffer.flush();
    LOG.fine("Buffer content: " + buffer.toString());
    return buffer.toString();
  }

  protected void writeFutureDates(List<String> dateStrings) throws IOException {
    List<Calendar> calendars = new ArrayList<Calendar>();
    for (String dateString : dateStrings) {
      Calendar calendar = new GregorianCalendar();
      try {
        calendar.setTime(new Date(Long.parseLong(dateString)));
        calendars.add(calendar);
      } catch (NumberFormatException e) {
        LOG.info("Invalid date: '" + dateStrings + "'");
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

  protected void deletePrevious() {
    File file = new File(GenerateDates.DATES_FUTURE);
    if (file.exists()) {
      if (!file.delete()) {
        throw new RuntimeException("Could not delete " + GenerateDates.DATES_FUTURE);
      }
    }
  }

}
