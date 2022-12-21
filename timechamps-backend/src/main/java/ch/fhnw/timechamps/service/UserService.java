package ch.fhnw.timechamps.service;

import ch.fhnw.timechamps.exception.UserNotFoundException;
import ch.fhnw.timechamps.model.User;
import ch.fhnw.timechamps.model.UserRole;
import ch.fhnw.timechamps.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Lukas Kipfer
 */

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList( //This User refers to the existing Class within Spring Framework!!
            new User(
                    "lukas.kipfer@students.fhnw.ch",
                    "password",
                    UserRole.ADMIN,
                    false,
                    true
            )
    );

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public UserDetails findUserByUsername(String username) {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user was found."))
                ;
    }
    public User signUpUser(User user) {

        //TODO: Implement Password Encoder
        /*
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
*/
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found."));
    }

    public void deleteUser (Long id) {
        userRepository.deleteUserById(id);
    }

    public Optional<User> findByUsername (String username) {
        Optional <User> userOptional = userRepository.findUserByUsername(username);
        return userOptional;
    }
}
