package ch.fhnw.timechamps.controller;

import ch.fhnw.timechamps.model.Employee;
import ch.fhnw.timechamps.model.User;
import ch.fhnw.timechamps.service.EmployeeService;
import ch.fhnw.timechamps.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=3120s
 */

@RestController
@RequestMapping("/employee") //defines a base url for the controller of this class
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserService userService;

    public EmployeeController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @GetMapping("/all") //whenever we navigate to this url, a list of all employees will be returned
    public ResponseEntity<List<Employee>> getAllEmployees() { //ResponseEntity lets us use HTTP-responses
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")   //this uses the base url defined in the RequestMapping and specifies further. The {id} is a path parameter.
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) { //note that the PathVariable(id) refers to the id parameter in the url
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, User user) {
        if (userService.findByUsername(user.getUsername()).isPresent() || employeeService.findEmployeeByEmail(employee.getEmail()).isPresent())        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Employee newEmployee = employeeService.addEmployee(employee);
        User newUser = userService.signUpUser(user);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) { //because there is no Employee expected in the body of the response, we put a "?" in the generic
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
