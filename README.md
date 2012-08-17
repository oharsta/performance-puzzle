## Performance Puzzle

First run the ```/performance-puzzle/src/main/java/com/zilverline/GenerateDates.java``` to generate the file ```/performance-puzzle/src/test/resources/dates.txt``` containing 500.000 dates (ms since epoch) with 50 times
 the ```..26505|end|begin|13180..``` sequence.
 
Then start with running ```/performance-puzzle/src/main/java/com/zilverline/ReadDates1.java``` (make sure to set the VM options to ```-Xprof -Xmx512m``` to see the profile report). Then start optimizing or run the ```ReadDates2...4``` to see the gradual improvement. The real challenge is improving after ```ReadDates4```.

This code was used in a performance workshop.

  
