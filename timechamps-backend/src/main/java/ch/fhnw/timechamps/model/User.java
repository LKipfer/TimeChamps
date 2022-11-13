package ch.fhnw.timechamps.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Lukas Kipfer
 */

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private UserType userType;
    private UserStatus userStatus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

