package ch.fhnw.timechamps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;

/**
 * @author Lukas Kipfer
 * Source for having to adjust daylight saving: https://www.baeldung.com/java-daylight-savings
 * Source for Clock class (for Mocking purposes): https://www.baeldung.com/java-clock
 * Source for Instant and Duration: https://www.baeldung.com/java-period-duration
 * Source for rounding formula: https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
 */

@Entity
public class Timestamps implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private double workTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        this.workTime = workTime;
    }

    public static ZonedDateTime createTimeStamp() {

        LocalDateTime localDateTimeBeforeDST = LocalDateTime.now();
        ZoneId zoneSwitzerland = ZoneId.of("Europe/Zurich");
        ZonedDateTime timestamp = localDateTimeBeforeDST.atZone(zoneSwitzerland);

        return timestamp;
    }

    public static ZonedDateTime createMockTimeStamp() {
        ZoneId zoneSwitzerland = ZoneId.of("Europe/Zurich");
        Clock timeStampClock = Clock.fixed(Instant.parse("2022-11-25T13:00:00.00Z"),
                ZoneId.of("Europe/Zurich"));

        ZonedDateTime timestamp = ZonedDateTime.now(timeStampClock).minusHours(1);
        return timestamp;
    }

    /**
     * ToDo: Add existing worktime to total
     */
    public static double calculateWorkTime(ZonedDateTime startTime, ZonedDateTime endTime) {

        double newWorktime = Math.round((double)Duration.between(startTime, endTime).toMinutes()/60 * 100d) / 100d;
        return newWorktime;
    }


}


