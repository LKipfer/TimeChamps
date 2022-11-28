package ch.fhnw.timechamps.controller;

import ch.fhnw.timechamps.model.Timestamps;
import ch.fhnw.timechamps.service.TimestampsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=3120s
 */

@RestController
@RequestMapping("/timestamps")
public class TimestampsController {

    private final TimestampsService timestampsService;

    public TimestampsController(TimestampsService timestampsService) {
        this.timestampsService = timestampsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Timestamps>> getAllTimestamps() {
        List<Timestamps> timestamp = timestampsService.findAllTimestamps();
        return new ResponseEntity<>(timestamp, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Timestamps> getTimestampsById(@PathVariable("id") Long id) {
        Timestamps timestamp = timestampsService.findTimestampsById(id);
        return new ResponseEntity<>(timestamp, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Timestamps> addTimestamp(@RequestBody Timestamps timestamp) {
        Timestamps newTimestamp = timestampsService.addTimestamp(timestamp);
        return new ResponseEntity<>(newTimestamp, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Timestamps> updateTimestamp(@RequestBody Timestamps timestamp) {
        Timestamps updateTimestamp = timestampsService.updateTimestamp(timestamp);
        return new ResponseEntity<>(updateTimestamp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTimestamp(@PathVariable("id") Long id) {
        timestampsService.deleteTimestamp(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
