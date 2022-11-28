package ch.fhnw.timechamps.service;

import ch.fhnw.timechamps.exception.UserNotFoundException;
import ch.fhnw.timechamps.model.User;
import ch.fhnw.timechamps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lukas Kipfer
 */

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        //employee.setEmployeeCode(UUID.randomUUID().toString());
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
}
