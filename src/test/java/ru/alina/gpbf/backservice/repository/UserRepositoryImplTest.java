package ru.alina.gpbf.backservice.repository;

import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import ru.alina.gpbf.backservice.Matcher;
import ru.alina.gpbf.backservice.UserData;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.repository.impl.UserRepository;

import static ru.alina.gpbf.backservice.UserData.USER_1;


class UserRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void checkSave() {
        User created = userRepository.save(UserData.getNew());
        long newId = created.getId();
        User newUser = new User(newId, 999L, "Stekhem");
        Matcher.match(created, newUser);
    }

    @Test
    void checkSave_duplicateTelegramId() {
        Assertions.assertThrows(DuplicateKeyException.class, () -> userRepository.save(new User(null, USER_1.getTelegramId(), "Stekhem")));
    }

    @Test
    void checkSave_duplicateUserName() {
        Assertions.assertThrows(DuplicateKeyException.class, () -> userRepository.save(new User(null, 999L, USER_1.getUserName())));
    }

    @Test
    void checkSave_NullTelegramId() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(new User(null, null, "Stekhem")));
    }

    @Test
    void checkSave_NullUserName() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(new User(null, 999L, null)));
    }

}