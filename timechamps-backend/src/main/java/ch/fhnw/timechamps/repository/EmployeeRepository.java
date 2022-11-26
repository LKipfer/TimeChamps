package ch.fhnw.timechamps.repository;

import ch.fhnw.timechamps.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=3120s
 */

//type long refers to the primary key which was previously created in the Employee class
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Both methods below are so called query methods. Spring understands what to do because the keywords "delete" and "find" are used.

    void deleteEmployeeById(Long id);

    //The reason why we used "Optional" instead of only "Employee" is for cases, that we do not find an Employee.
    Optional<Employee> findEmployeeById(Long id);
}
