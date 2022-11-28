package ch.fhnw.timechamps.repository;

import ch.fhnw.timechamps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author Lukas Kipfer
 */

public interface UserRepository extends JpaRepository<User, Long> {

    void deleteUserById(Long id);

    //The reason why we used "Optional" instead of only "Employee" is for cases, that we do not find an Employee.
    Optional<User> findUserById(Long id);

}
