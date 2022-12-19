package ch.fhnw.timechamps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=QwQuro7ekvc&t=5267s
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username; //usernames = mail addresses
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @JsonIgnore
    private Boolean locked;
    @JsonIgnore
    private Boolean enabled;

    public User(String username,
                String password,
                UserRole userRole,
                Boolean locked,
                Boolean enabled) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.locked = false;
        this.enabled = enabled;
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //not implemented here -> just for Spring
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //not implemented here -> just for Spring
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



/*  OLD CODE BEFORE USING SPRING WEB SECURITY
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private UserType userType;
    private UserStatus userStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static boolean authorizationCheck (UserRole userRole) {

        boolean isValid = false;

        if (userRole == UserRole.ADMIN) {
            isValid = true;
        }

        return isValid;
    }


         /*

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
    */

}

