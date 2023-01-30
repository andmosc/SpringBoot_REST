package ru.andmosc.authorizationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.andmosc.authorizationService.dto.UserPasswordDTO;
import ru.andmosc.authorizationService.models.Authorities;
import ru.andmosc.authorizationService.serviece.AuthorizationService;
import ru.andmosc.authorizationService.util.InvalidCredentials;
import ru.andmosc.authorizationService.util.InvalidPassword;
import ru.andmosc.authorizationService.util.UnauthorizedUser;
import ru.andmosc.authorizationService.util.UserErrorResponse;

import javax.validation.Valid;
import java.util.List;

@RestController("/")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("authorize")
    public List<Authorities> getAuthorities(@RequestBody @Valid UserPasswordDTO userPasDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new InvalidCredentials(errorMsg.toString());
        }
        return authorizationService.getAuthorities(userPasDTO);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(InvalidCredentials e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UnauthorizedUser e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(InvalidPassword e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
