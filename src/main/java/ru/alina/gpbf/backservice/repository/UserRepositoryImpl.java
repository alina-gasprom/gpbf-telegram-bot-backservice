package ru.alina.gpbf.backservice.repository;


import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.jooq.public_.tables.Users;
import ru.alina.gpbf.backservice.repository.impl.UserRepository;


import static ru.alina.gpbf.backservice.jooq.public_.Sequences.GLOBAL_SEQ;


@Repository
public class UserRepositoryImpl implements UserRepository {
    private final DSLContext dsl;

    public UserRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public User save(User user) {
        return dsl.insertInto(Users.USERS)
                .values(dsl.nextval(GLOBAL_SEQ), user.getTelegramId(), user.getUserName())
                .returning()
                .fetchOne()
                .into(User.class);

    }
}
