package ch.fhnw.timechamps.model;

import java.time.Duration;
import java.time.Instant;

public class Timestamps {

    Instant start = Instant.now();




        System.out.println("Start Time = " + start);
        System.out.println("Timer started, please enter any input to stop timer.");

    Instant end = Instant.now();
    Duration timeElapsed = Duration.between(start, end);
        System.out.println("End Time = " + end);
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

}
