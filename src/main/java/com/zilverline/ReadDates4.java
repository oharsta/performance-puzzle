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
