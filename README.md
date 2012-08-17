## Performance Puzzle

First clone the repo. Fire up your favorite IDE and first run:

```/performance-puzzle/src/main/java/com/zilverline/GenerateDates.java``` 

to generate the file 

```/performance-puzzle/src/test/resources/dates.txt``` 

containing 500.000 dates (ms since epoch) separated by ```|``` with 50 times the ```..26505|end|begin|13180..``` sequence.
 
Then configure your run profiles to include the following VM arguments:

```-Xprof -Xmx512m```

If you don't you'll get the following:

    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	    at java.util.Arrays.copyOf(Arrays.java:2882)
	    at java.lang.AbstractStringBuilder.expandCapacity(AbstractStringBuilder.java:100)
	    at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:390)
	    at java.lang.StringBuilder.append(StringBuilder.java:119)
	    at com.zilverline.ReadDates1.writeFutureDates(ReadDates1.java:94)
	    at com.zilverline.ReadDates1.perform(ReadDates1.java:71)
	    at com.zilverline.ReadDates1.main(ReadDates1.java:64)
	
And you'll also miss out on the profiler output:

<pre>
Flat profile of 0.00 secs (1 total ticks): Thread-2

  Interpreted + native   Method                        
100.0%     1  +     0    java.lang.ProcessEnvironment.<clinit>
100.0%     1  +     0    Total interpreted


Flat profile of 26.62 secs (1550 total ticks): main

  Interpreted + native   Method                        
 10.5%   163  +     0    java.util.Arrays.copyOf
  6.5%   100  +     0    java.util.Arrays.copyOfRange
  3.5%    55  +     0    java.lang.StringBuilder.append
  0.6%    10  +     0    java.util.Calendar.<init>
  0.3%     4  +     0    java.lang.AbstractStringBuilder.<init>
  0.2%     0  +     3    java.lang.System.currentTimeMillis
  0.2%     0  +     3    java.io.FileOutputStream.writeBytes
  0.2%     3  +     0    java.util.AbstractCollection.toArray
  0.1%     0  +     2    java.io.FileInputStream.read
  0.1%     2  +     0    java.lang.Long.toString
  0.1%     0  +     2    java.lang.System.arraycopy
  0.1%     1  +     1    java.lang.Class.forName0
  0.1%     2  +     0    java.util.AbstractCollection.toString
  0.1%     2  +     0    java.util.regex.Pattern.split
  0.1%     0  +     1    java.io.FileOutputStream.open
  0.1%     1  +     0    java.security.Provider.getTypeAndAlgorithm
  0.1%     1  +     0    java.util.regex.Pattern$Start.match
  0.1%     1  +     0    java.lang.StringCoding$StringDecoder.decode
  0.1%     1  +     0    java.math.BigInteger.squareToLen
  0.1%     1  +     0    java.util.jar.JarFile.initializeVerifier
  0.1%     1  +     0    java.util.zip.Inflater.reset
  0.1%     1  +     0    sun.nio.cs.SingleByteDecoder.decodeArrayLoop
  0.1%     1  +     0    java.lang.Long.parseLong
  0.1%     1  +     0    java.util.GregorianCalendar.computeFields
  0.1%     1  +     0    java.util.ArrayList.add
 23.8%   357  +    12    Total interpreted (including elided)

     Compiled + native   Method                        
  8.8%   137  +     0    java.util.AbstractCollection.toString
  1.3%     2  +    18    com.zilverline.ReadDates1.writeFutureDates
  0.5%     0  +     7    java.util.GregorianCalendar.<init>
  0.3%     1  +     4    java.util.regex.Pattern.split
  0.1%     0  +     1    java.util.GregorianCalendar.computeFields
  0.1%     0  +     1    sun.nio.cs.SingleByteDecoder.decodeArrayLoop
  0.1%     0  +     1    java.util.Calendar.getTime
 11.1%   140  +    32    Total compiled

         Stub + native   Method                        
 52.9%     0  +   820    java.io.FileInputStream.read
 12.1%     0  +   188    java.lang.System.arraycopy
 65.0%     0  +  1008    Total stub

  Thread-local ticks:
  0.1%     1             Class loader


Flat profile of 0.00 secs (1 total ticks): DestroyJavaVM

  Thread-local ticks:
100.0%     1             Blocked (of total)


Global summary of 26.63 seconds:
100.0%  2180             Received ticks
 27.9%   609             Received GC ticks
  2.9%    63             Compilation
  1.0%    21             Other VM operations
  0.0%     1             Class loader
</pre>

Start your performance puzzle with running 

```/performance-puzzle/src/main/java/com/zilverline/ReadDates1.java``` 

And have a go at improving the performance.

To see the gradual improvements and final version just have a look at the changes made in ```ReadDates2.. to ..4```. The real challenge is improving after ```ReadDates4```;-)

This code was used in a performance workshop.

  
