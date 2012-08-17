/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
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
