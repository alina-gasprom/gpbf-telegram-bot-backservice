package ru.alina.gpbf.backservice.service;

import org.springframework.stereotype.Service;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.gateway.UserGateway;
import ru.alina.gpbf.backservice.service.impl.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserGateway userGateway;

    public RegisterServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void createUser(User user) {
        userGateway.createUser(user);
    }
}
