package ch.fhnw.timechamps.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=b9O9NI-RJ3o&t=1598s
 */

@Repository
public class UserDao {

    /**
     * Here we created the one admin account, that was required to be added manually (-> see Project Requirements).
     */

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList( //This User refers to the existing Class within Spring Framework!!
            new User(
                    "lukas.kipfer@students.fhnw.ch",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            )
    );

    public UserDetails findUserByEmail(String email) {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user was found."))
        ;
    }

}
