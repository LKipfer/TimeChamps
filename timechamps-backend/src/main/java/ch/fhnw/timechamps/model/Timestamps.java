package ch.fhnw.timechamps.model;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Lukas Kipfer
 */

public class Timestamps {

    Instant start = Instant.now();

    Instant end = Instant.now();


    public Duration calculateWorktime () {
        Duration total = (Duration.between(this.start, this.end));
        return total;
    }

}


