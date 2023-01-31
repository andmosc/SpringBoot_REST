package ru.andmosc.authorizationService.servieces;

import org.springframework.stereotype.Service;
import ru.andmosc.authorizationService.models.Authorities;
import ru.andmosc.authorizationService.dto.UserPasswordDTO;
import ru.andmosc.authorizationService.repositories.UserRepository;
import ru.andmosc.authorizationService.util.UnauthorizedUser;

import java.util.List;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(UserPasswordDTO userPasswordDTO) {
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(userPasswordDTO);
        if (userAuthorities == null || userAuthorities.isEmpty()) {
            throw new UnauthorizedUser("Unknown user: " + userPasswordDTO.getLogin());
        }
        return userAuthorities;
    }
}
