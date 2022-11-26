package ch.fhnw.timechamps.repository;

import ch.fhnw.timechamps.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=3120s
 */

//type long refers to the primary key which was previously created in the Employee class
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
