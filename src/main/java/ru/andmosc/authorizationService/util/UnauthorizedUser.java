package ru.andmosc.authorizationService.util;

public class UnauthorizedUser extends RuntimeException{
    public UnauthorizedUser(String message) {
        super(message);
    }
}
