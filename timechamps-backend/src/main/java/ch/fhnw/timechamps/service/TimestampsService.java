package ch.fhnw.timechamps.service;

import ch.fhnw.timechamps.exception.TimestampNotFoundException;
import ch.fhnw.timechamps.model.Timestamps;
import ch.fhnw.timechamps.repository.TimestampsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimestampsService {

    private final TimestampsRepository timestampsRepository;

    @Autowired
    public TimestampsService(TimestampsRepository timestampsRepository) {
        this.timestampsRepository = timestampsRepository;
    }

    public Timestamps addTimestamp(Timestamps timestamp) {
        //timestamp.setEmployeeCode(UUID.randomUUID().toString());
        return timestampsRepository.save(timestamp);
    }

    public List<Timestamps> findAllTimestamps() {
        return timestampsRepository.findAll();
    }

    public Timestamps updateTimestamp(Timestamps timestamp) {
        return timestampsRepository.save(timestamp);
    }

    public Timestamps findTimestampsById(Long id) {
        return timestampsRepository.findTimestampsById(id)
                .orElseThrow(() -> new TimestampNotFoundException("Timestamp by id " + id + "was not found."));
    }

    public void deleteTimestamp (Long id) {
        timestampsRepository.deleteTimestampsById(id);
    }
}
