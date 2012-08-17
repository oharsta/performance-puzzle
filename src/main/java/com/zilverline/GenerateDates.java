package com.zilverline;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Generate the 500.000 dates (ms since epoch) with 50 times
 * ..26505|end|begin|13180..
 */
public class GenerateDates {

  static String DATES = "src/test/resources/dates.txt";
  static String DATES_FUTURE = "src/test/resources/datesFuture.txt";

  public static void main(String[] args) throws IOException {
    File file = new File(DATES);
    if (file.exists()) {
      if (!file.delete()) {
        throw new RuntimeException("Could not delete " + DATES);
      }
    }
    long now = System.currentTimeMillis();
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(DATES)));
    for (int i = 0; i < 500000; i++) {
      double r = Math.random();
      double d = (r * 1000 * 64 * 64 * 24 * 30 * 6) * (r > 0.5 ? -1 : 1);
      long time = (long) (now + d);
      out.writeBytes(time + "|");
      if (i % 10000 == 0) {
        out.writeBytes("end|begin|");
      }
    }
    out.close();
  }
}
