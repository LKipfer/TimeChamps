package ch.fhnw.timechamps.controller.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=b9O9NI-RJ3o&t=1598s
 */

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;

}
