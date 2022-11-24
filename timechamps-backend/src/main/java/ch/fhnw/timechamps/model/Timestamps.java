package ch.fhnw.timechamps.model;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

/**
 * @author Lukas Kipfer
 * Source for having to adjust daylight saving: https://stackoverflow.com/questions/26886703/java-time-does-the-cet-time-zone-considers-daylight-saving-time
 * Source for Clock class (for Mocking purposes): https://www.baeldung.com/java-clock
 * Source for Instant and Duration: https://www.baeldung.com/java-period-duration
 * Source for rounding formula: https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
 */

public class Timestamps {

    ZoneId zoneEurope = ZoneId.of("Europe/Zurich");

    /** This code is used for mocking / testing
    _______________________________________________________________________
    Clock startClock = Clock.system(zoneEurope);
    Clock endClock = Clock.fixed(Instant.parse("2022-11-25T01:50:00.00Z"),
            ZoneId.of("Europe/Zurich"));
    _______________________________________________________________________
     */

    /**
     * Todo: Implement startTime and endTime triggers
     * Todo: Create separate functions without daylight saving
     */

    Clock startClock = Clock.system(zoneEurope);
    Clock endClock = Clock.system(zoneEurope);
    Instant startTime = startClock.instant();
    Instant endTime = endClock.instant();



    public double calculateWorkTime () {
        double workTime = Math.round((double)Duration.between(startTime, endTime).minusHours(1).toMinutes()/60 * 100d) / 100d;
        return workTime;
    }


}


