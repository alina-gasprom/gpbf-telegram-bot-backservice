package ru.alina.gpbf.backservice.gateway;

import org.springframework.stereotype.Component;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.repository.impl.UserRepository;

@Component
public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;

    public UserGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user.getUserName().isBlank()) {
            throw new RuntimeException("user name is blank");
        }
        User newUser = userRepository.save(user);
        if (newUser == null) {
            throw new RuntimeException("user could not be created");
        }
        return newUser;
    }
}
