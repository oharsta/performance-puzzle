package com.zilverline;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Fourth version (< 1.0 seconds) of ReadDates. Run with JMV options:
 * 
 * -Xprof -Xmx512m
 * 
 * 
 */
public class ReadDates4 extends ReadDates3 {

  public static void main(String[] args) throws IOException {
    ReadDates4 main = new ReadDates4();
    main.perform(main);
  }

  @Override
  protected void writeFutureDates(List<String> dateStrings) throws IOException {
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
        GenerateDates.DATES_FUTURE)));
    long now = System.currentTimeMillis();
    for (String dateString : dateStrings) {
      try {
        long time = Long.parseLong(dateString);
        if (time > now) {
          out.writeBytes(time + "|");
        }
      } catch (NumberFormatException e) {
      }
    }
    out.close();
  }

}
