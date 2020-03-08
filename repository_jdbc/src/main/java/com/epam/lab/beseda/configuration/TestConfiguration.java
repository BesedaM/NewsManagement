package com.epam.lab.beseda.configuration;

import com.epam.lab.beseda.util.DatabaseConfigure;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

import static com.epam.lab.beseda.util.LoggerName.ERROR_LOGGER;

@Configuration
@Import({DAORowMapperConfig.class, DAOResultSetExtractorConfig.class})
@ComponentScan({"com.epam.lab.beseda.dao.entitydao", "com.epam.lab.beseda.dao.util"})
public class TestConfiguration {

    private Logger log = LogManager.getLogger(ERROR_LOGGER);

    @Bean
    public DataSource getDataSource() {
        EmbeddedPostgres database = null;
        try {
            database = EmbeddedPostgres.builder().start();
        } catch (IOException e) {
            log.error(e);
        }
        return database.getPostgresDatabase();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean(initMethod = "createEmptyDatabase", destroyMethod = "deleteDatabase")
    public DatabaseConfigure getTestDatabaseConfigure() {
        return new DatabaseConfigure(getDataSource());
    }

}
