package ch.fhnw.timechamps.service;

import ch.fhnw.timechamps.exception.UserNotFoundException;
import ch.fhnw.timechamps.model.Employee;
import ch.fhnw.timechamps.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=3120s
 *
 * Note to self: The service uses commands which are given by the JpaRepository and therefore not separately coded.
 * To see which commands are available, check interface EmployeeRepository and click on JpaRepository.
 */

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        //generates a random UUID as reference point and parses it to string
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found.")); //orElseThrow is needed, because we defined this method as "Optional" in the EmployeeRepository
    }

    public void deleteEmployee (Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
