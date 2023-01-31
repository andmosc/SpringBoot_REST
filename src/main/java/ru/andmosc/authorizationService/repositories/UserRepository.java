package ru.andmosc.authorizationService.repositories;

import org.springframework.stereotype.Repository;
import ru.andmosc.authorizationService.dto.UserPasswordDTO;
import ru.andmosc.authorizationService.models.Authorities;
import ru.andmosc.authorizationService.models.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.andmosc.authorizationService.models.Authorities.*;

@Repository
public class UserRepository {
    private Map<String, User> users = new ConcurrentHashMap<>();

    public UserRepository() {
        users.put("And",
                new User("And", "Moscovets", "And",
                "123456", "email@mail.ru", List.of(WRITE, DELETE)));
        users.put("Max",
                new User("Max", "Ivanov", "Max",
                "64321", "email@gmail.ru", List.of(RED)));
    }

    public UserRepository(Map<String, User> users) {
        this.users = users;
    }

    public List<Authorities> getUserAuthorities(UserPasswordDTO userPasswordDTO) {
        return users.values().stream().filter(user -> user.getLogin().equals(userPasswordDTO.getLogin())
                && user.getPassword().equals(userPasswordDTO.getPassword())).findAny()
                .orElse(new User()).getAuthoritiesList();
    }
}
