package ru.alina.gpbf.backservice.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.alina.gpbf.backservice.AbstractTest;

@SpringBootTest
@Sql(scripts = {"classpath:static/db/initDB.sql", "classpath:static/db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class AbstractRepositoryTest extends AbstractTest {
}
