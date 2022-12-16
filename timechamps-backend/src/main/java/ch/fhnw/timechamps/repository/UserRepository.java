package ch.fhnw.timechamps.repository;

import ch.fhnw.timechamps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * @author Lukas Kipfer
 */

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    void deleteUserById(Long id);

    //The reason why we used "Optional" instead of only "Employee" is for cases, that we do not find an Employee.
    Optional<User> findUserById(Long id);

}
