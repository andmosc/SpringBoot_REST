package ru.andmosc.authorizationService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserPasswordDTO {
    @NotBlank(message = "login should not be empty")
    @Size(min = 2, max = 30, message = "login should be between 2 and 30 characters")
    private String login;
    @NotBlank(message = "password should not be empty")
    @NotEmpty(message = "password should not be empty")
    @Size(min = 5, max = 30, message = "password should be between 5 and 30 characters")
    private String password;

    public UserPasswordDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
