package ru.alina.gpbf.backservice.rest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.alina.gpbf.backservice.AbstractTest;

@AutoConfigureMockMvc
@Sql(scripts = {"classpath:static/db/initDB.sql", "classpath:static/db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AbstractControllerTest extends AbstractTest {
}
