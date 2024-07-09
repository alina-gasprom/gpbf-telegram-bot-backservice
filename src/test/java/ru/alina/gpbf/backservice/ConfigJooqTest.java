package ru.alina.gpbf.backservice;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ConfigJooqTest extends AbstractTest {
    @Autowired
    private DSLContext dSLContext;

    @Test
    public void dialectShouldBePickedUp() {
        Assertions.assertEquals(dSLContext.configuration().dialect(), SQLDialect.POSTGRES);
    }
}
