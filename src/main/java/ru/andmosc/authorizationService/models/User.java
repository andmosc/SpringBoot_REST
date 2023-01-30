package ru.andmosc.authorizationService.models;

import java.util.List;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private List<Authorities> authoritiesList;

    public User(String name, String surname, String login, String password, String email, List<Authorities> authoritiesList) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.authoritiesList = authoritiesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    public boolean wordPasses(String password) {
        return this.password.equals(password);
    }
}
