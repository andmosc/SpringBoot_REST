package ru.andmosc.authorizationService.repositories;

import org.springframework.stereotype.Repository;
import ru.andmosc.authorizationService.dto.UserPasswordDTO;
import ru.andmosc.authorizationService.models.Authorities;
import ru.andmosc.authorizationService.models.User;
import ru.andmosc.authorizationService.util.InvalidPassword;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.andmosc.authorizationService.models.Authorities.*;

@Repository
public class UserRepository {
    private final Map<String, User> users;

    public UserRepository() {
        users = new ConcurrentHashMap<>();
        addUser(new User("And", "Moscovets", "Mosc",
                "123456", "email@mail.ru", List.of(WRITE, DELETE)));
    }

    public UserRepository(Map<String, User> users) {
        this.users = users;
    }

    private void addUser(User user) {
        users.put(user.getLogin(), user);
    }

    public List<Authorities> getUserAuthorities(UserPasswordDTO userPasswordDTO) {
        User user = users.get(userPasswordDTO.getLogin());
        if (user != null) {
            if (!user.wordPasses(userPasswordDTO.getPassword())) {
                throw new InvalidPassword("Invalid password");
            }
            return user.getAuthoritiesList();
        }
        return null;
    }
}
