package ru.alina.gpbf.backservice.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.alina.gpbf.backservice.Matcher;
import ru.alina.gpbf.backservice.UserData;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.repository.impl.UserRepository;


class UserGatewayImplTest {

    @Test
    void createUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User created = new User(999L, UserData.getNew().getTelegramId(), UserData.getNew().getUserName());
        Mockito.when(userRepository.save(created)).thenReturn(created);
        UserGatewayImpl userGateway = new UserGatewayImpl(userRepository);

        User result = userGateway.createUser(created);

        Matcher.match(result, created);
    }

    @Test
    void createUser_BlankUserName() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User created = new User(999L, UserData.getNew().getTelegramId(), "  ");
        Mockito.when(userRepository.save(created)).thenReturn(created);
        UserGatewayImpl userGateway = new UserGatewayImpl(userRepository);

        Assertions.assertThrows(RuntimeException.class, () -> userGateway.createUser(created));

    }
}