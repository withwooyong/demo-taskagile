package com.demo.demotaskagile.web.apis;

import com.demo.demotaskagile.domain.application.UserService;
import com.demo.demotaskagile.domain.application.commands.RegisterCommand;
import com.demo.demotaskagile.domain.model.user.EmailAddressExistsException;
import com.demo.demotaskagile.domain.model.user.RegistrationException;
import com.demo.demotaskagile.domain.model.user.UsernameExistsException;
import com.demo.demotaskagile.web.payload.RegistrationPayload;
import com.demo.demotaskagile.web.results.ApiResult;
import com.demo.demotaskagile.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationApiController extends AbstractBaseController {

    private UserService service;

    public RegistrationApiController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload,
                                              HttpServletRequest request) {
        try {
            RegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);

            service.register(command);
            return Result.created();
        } catch (RegistrationException e) {
            String errorMessage = "Registration failed";
            if (e instanceof UsernameExistsException) {
                errorMessage = "Username already exists";
            } else if (e instanceof EmailAddressExistsException) {
                errorMessage = "Email address already exists";
            }
            return Result.failure(errorMessage);
        }
    }
}
