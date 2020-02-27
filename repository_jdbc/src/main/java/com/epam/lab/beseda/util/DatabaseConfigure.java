package com.epam.lab.beseda.util;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.epam.lab.beseda.util.DBEntityTable.*;

public class DatabaseConfigure {

    private static Logger log = LogManager.getLogger(LoggerName.ERROR_LOGGER);

    private DataSource dataSource;

    public DatabaseConfigure(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    private static final String ENCODING = "UTF-8";
    private static final String DROP_DB_STATEMENT = "DROP SCHEMA news_management CASCADE;";
    private static final String TRUNCATE_TABLE_STATEMENT = "TRUNCATE news_management.table CASCADE";
    private static final String CREATE_EMPTY_DB_SCRIPT = "news_management.sql";
    private static final String FILL_DATABASE_WITH_DATA = "news_management_data.sql";


    private void createEmptyDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(createScript(CREATE_EMPTY_DB_SCRIPT));
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void fillDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(createScript(FILL_DATABASE_WITH_DATA));
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private BufferedReader createScript(String fileName) {
        File dbScript = new File(classLoader.getResource(fileName).getFile());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(dbScript), ENCODING));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            log.error(e);
        }
        return br;
    }

    public void deleteDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            Statement st = connection.createStatement();
            st.execute(DROP_DB_STATEMENT);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void cleanDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            truncateTable(connection, T_USER);
            truncateTable(connection, T_AUTHOR);
            truncateTable(connection, T_NEWS);
            truncateTable(connection, T_NEWS_AUTHOR);
            truncateTable(connection, T_NEWS_TAG);
            truncateTable(connection, T_ROLES);
            truncateTable(connection, T_TAG);
        } catch (SQLException e) {
            log.error(e);
        }
    }


    private void truncateTable(Connection conn, String tableName) throws SQLException {
        Statement st = conn.createStatement();
        String sql = TRUNCATE_TABLE_STATEMENT.replace("table", tableName);
        st.execute(sql);
    }
}
