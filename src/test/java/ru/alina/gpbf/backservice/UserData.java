package ru.alina.gpbf.backservice;

import ru.alina.gpbf.backservice.domain.User;

public class UserData {
    public final static User USER_1 = new User(1L, 100L, "Kostya");
    public final static User USER_2 = new User(2L, 200L, "Vadim");
    public final static User USER_3 = new User( 3L,300L, "Yan");
    public final static User USER_4 = new User( 4L,400L, "Maks");

    public static User getNew() {
        return new User (null, 999L, "Stekhem");
    }
}
