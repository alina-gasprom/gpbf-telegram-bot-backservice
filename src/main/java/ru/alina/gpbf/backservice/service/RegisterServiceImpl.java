package ru.alina.gpbf.backservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.gateway.UserGateway;
import ru.alina.gpbf.backservice.service.impl.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);
    private final UserGateway userGateway;

    public RegisterServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void createUser(User user) {
        User created = userGateway.createUser(user);
        log.info("User created: {}", created);
    }
}
