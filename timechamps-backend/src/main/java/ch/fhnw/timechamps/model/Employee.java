package ch.fhnw.timechamps.model;

import javax.persistence.*;
import java.io.Serializable;


// Coded by Lukas Kipfer

@Entity // This ensures that this class is mapped to the configured database. From Spring JPA repository
public class Employee implements Serializable {
    @Id                                                                                                                 //Determines the primary key in the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)                                                                 //Check why strategy and what the generation types are
    @Column(nullable = false, updatable = false)                                                                        //Makes that this datatype cant be changed or nulled after creation.
    private Long id;
    private String surname;
    private String name;
    private String jobTitle;
    private String email;
    @Column(nullable = false, updatable = false)
    private String employeeCode;

    public Employee () {}

    public Employee(String surname, String name, String jobTitle, String email, String employeeCode) {
        this.surname = surname;
        this.name = name;
        this.jobTitle = jobTitle;
        this.email = email;
        this.employeeCode = employeeCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
