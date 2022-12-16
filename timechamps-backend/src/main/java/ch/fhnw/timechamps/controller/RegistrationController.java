package ch.fhnw.timechamps.controller;

import ch.fhnw.timechamps.controller.requests.RegistrationRequest;
import ch.fhnw.timechamps.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=QwQuro7ekvc&t=5267s
 */


@RestController
@RequestMapping(path = "/user/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

}
