package ch.fhnw.timechamps.model;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Lukas Kipfer
 */

public class Timestamps {

    Instant start = Instant.now();

    Instant end = Instant.now();

    Long totalTime;



    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Duration calculateTimestamp () {
        Duration total = (Duration.between(this.start, this.end));
        return total;
    }

    public Long updateTimestamp () {
        totalTime = totalTime + this.totalTime;
        return totalTime;
    }

    public void parseDuration () {

        /**
         * source: https://www.geeksforgeeks.org/duration-parsecharsequence-method-in-java-with-examples/
         * usage: incase we need to parse existing time entries for editing or calculation purposes
         */

        // Get the text → Needs to be automated!
        String time = "P2DT3H4M";

        // Duration using parse() method
        try {
            Duration duration
                    = Duration.parse(time);

            System.out.println(duration.getSeconds());
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

}


