package ch.fhnw.timechamps.repository;

import ch.fhnw.timechamps.model.Timestamps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Lukas Kipfer
 */

public interface TimestampsRepository extends JpaRepository<Timestamps, Long> {

    void deleteTimestampsById(Long id);

    Optional<Timestamps> findTimestampsById(Long id);
}
