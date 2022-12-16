package ch.fhnw.timechamps.controller.requests;

import lombok.*;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=QwQuro7ekvc&t=5267s
 */

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String username;
    private final String email;
    private final String password;

}
