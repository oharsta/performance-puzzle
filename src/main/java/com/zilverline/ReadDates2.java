package com.zilverline;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Second (still slow version +/- 17 seconds) of ReadDates. Run with JMV
 * options:
 * 
 * -Xprof -Xmx512m
 * 
 * And look for:
 * 
 * Interpreted + native Method
 * 
 * 24.9% 178 + 0 java.util.Arrays.copyOf
 * 
 * Compiled + native Method
 * 
 * 19.3% 138 + 0 java.util.AbstractCollection.toString
 * 
 * Stub + native Method
 * 
 * 25.2% 0 + 180 java.lang.System.arraycopy
 * 
 * 
 */
public class ReadDates2 extends ReadDates1 {

  public static void main(String[] args) throws IOException {
    ReadDates2 main = new ReadDates2();
    main.perform(main);
  }

  /*
   * Faster read
   */
  @Override
  protected String readDates() throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    InputStream is = new FileInputStream(GenerateDates.DATES);
    byte[] bytes = new byte[8192];
    int len = 0;
    while ((len = is.read(bytes)) != -1) {
      buffer.write(bytes, 0, len);
    }
    buffer.flush();
    LOG.fine("Buffer content: " + buffer.toString());
    return buffer.toString();
  }

}
