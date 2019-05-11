package ru.inno.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Подключение к Базе Данных
 */
public class ConnectionManager implements IConnectionManager {
    private static final Logger logger = LogManager.getLogger(ConnectionManager.class);
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String URL_DB = "jdbc:mysql://localhost:3307/";
    private static final String DATABASE_NAME = "course_work";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final ConnectionManager INSTANCE = new ConnectionManager();
    private Connection connection;

    private ConnectionManager() {
        try {
            Class.forName(DRIVER_NAME);
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "utf-8");
            connection = DriverManager.getConnection(URL_DB + DATABASE_NAME, properties);
            logger.info("Connection");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    public static synchronized ConnectionManager getInstance() {
        logger.info("getInstance");
        return INSTANCE;
    }

    public Connection getConnection() {
        logger.info("getConnection");
        return connection;
    }
/*
    InitialContext initContext= new InitialContext();
    DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/appname");
    Connection connection = ds.getConnection();*/


}
