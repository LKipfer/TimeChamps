package ch.fhnw.timechamps.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {

    @Id
    //Determines the primary key in the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)                                                                 //Check why strategy and what the generation types are
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private UserType userType;
    private UserStatus userStatus;

}
