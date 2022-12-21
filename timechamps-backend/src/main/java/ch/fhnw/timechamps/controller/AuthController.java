package ch.fhnw.timechamps.controller;

import ch.fhnw.timechamps.config.JwtUtils;
import ch.fhnw.timechamps.controller.requests.AuthenticationRequest;
import ch.fhnw.timechamps.model.User;
import ch.fhnw.timechamps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=b9O9NI-RJ3o&t=1598s
 */

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request // The whole authentication process gets delegated to Spring
    ) {
        final Optional<User> optionalUser = userService.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        } else {
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }
}
