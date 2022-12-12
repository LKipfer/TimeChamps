package ch.fhnw.timechamps.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Lukas Kipfer
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private UserType userType;
    private UserStatus userStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public static boolean passwordCheck(String password) {

        /**
         Checking for Password Validation:
         - At least 8 characters long
         - At max 30 characters long
         - Must have at least one special character
         - Must have at least one lowercase character
         - Must have at least one uppercase character

         Source: https://java2blog.com/validate-password-java/
         */

        boolean isValid = true;

        if (password.length() > 30 || password.length() < 8)
        {
            System.out.println("Password must be less than 30 and more than 8 characters in length.");
            isValid = false;
        }

        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            System.out.println("Password must have at least one uppercase character");
            isValid = false;
        }

        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            System.out.println("Password must have at least one lowercase character");
            isValid = false;
        }

        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            System.out.println("Password must have at least one number");
            isValid = false;
        }
        String specialChars = "(.*[@#$%^&+=].*$)";
        if (!password.matches(specialChars ))
        {
            System.out.println("Password must have at least one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }

    public static boolean authorizationCheck (UserType usertype) {

        boolean isValid = false;

        if (usertype == UserType.Admin) {
            isValid = true;
        }

        return isValid;
    }

}

