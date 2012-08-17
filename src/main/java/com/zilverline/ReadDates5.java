package com.zilverline;

import java.io.IOException;

/**
 * Fifth version (< ?? seconds) of ReadDates. Run with JMV options:
 * 
 * -Xprof -Xmx512m
 * 
 * 
 */
public class ReadDates5 extends ReadDates4 {

  public static void main(String[] args) throws IOException {
    ReadDates5 main = new ReadDates5();
    main.perform(main);
  }

  //TODO optimize...

}
