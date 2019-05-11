package ru.inno.db.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import java.time.format.DateTimeFormatter;

public class ClientDAO {

    private static final Logger logger = LogManager.getLogger(ClientDAO.class);
    private static IConnectionManager manager;

    static {
        logger.info("static connection");
        manager = ConnectionManager.getInstance();
    }

    public ClientDAO() {
    }

    public static List<Client> getAll() throws ClientDAO.ClientDAOException {
        ArrayList clientList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (resultSet.next()) {

                Client client = new Client(
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getBoolean("isAuthorized"),
                        resultSet.getDate("dateOfBirth").getTime(),
                        resultSet.getString("passportData"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                clientList.add(client);
                System.out.println(client.toString());

            }
            logger.info(clientList.toString());
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
        return clientList;

    }

    public static List<Client> getById(int id) throws ClientDAO.ClientDAOException {
        ArrayList clientList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE id=" + id);
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getBoolean("isAuthorized"),
                        resultSet.getDate("dateOfBirth").getTime(),
                        resultSet.getString("passportData"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                clientList.add(client);
            }
            logger.info(clientList.toString());
            return clientList;
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
    }

    public static void updateOne(int id, String lastName) throws ClientDAO.ClientDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE clients SET lastName= ? WHERE id=?");
            statement.setString(1, lastName);
            statement.setInt(2, id);
            //statement.executeUpdate();
            if (statement.executeUpdate() == 1) {
                logger.info("client updated");

            }
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
    }

    public static void updateAll(String lastName) throws ClientDAO.ClientDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE clients SET lastName= ?");
            statement.setString(1, lastName);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
    }

    public static void deleteAll() throws ClientDAO.ClientDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM clients");
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
    }

    public static void deleteById(int id) throws ClientDAO.ClientDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM clients where id = ? ");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
    }

    public static int insertOne(Client client) {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO clients (" +
                            "firstName,lastName, secondName, dateOfBirth, email, password," +
                            " isAuthorized, passportData) VALUES (?, ?, ?, ?,?,?,?,?)");
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getSecondName());
            statement.setDate(4, java.sql.Date.valueOf(
                    String.format("%1$tY-%1$tm-%1$td", client.getDateOfBirth())));
            statement.setString(5, client.getEmail());
            statement.setString(6, client.getPassword());
            statement.setBoolean(7, client.isAuthorized());
            statement.setString(8, client.getPassportData());
            logger.debug(statement.toString());
            // logger.info(((PreparedStatement) statement).);
            if (statement.executeUpdate() == 1) {
                logger.info("client added");
                // logger.info(statement. + " " + e.getMessage());
                return 0;
            }
        } catch (SQLException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            return e.getErrorCode();
        }
        logger.error("FALSE");
        return 1;
    }

    public static void insertAll(List<Client> clients) throws ClientDAO.ClientDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO clients (" +
                            "firstName,lastName, secondName, dateOfBirth, email, password," +
                            " isAuthorized, passportData) VALUES (?, ?, ?, ?,?,?,?,?)");

            for (Client client : clients) {
                statement.setString(1, client.getFirstName());
                statement.setString(2, client.getLastName());
                statement.setString(3, client.getSecondName());
                statement.setDate(4, java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$td", client.getDateOfBirth())));
                statement.setString(5, client.getEmail());
                statement.setString(6, client.getPassword());
                statement.setBoolean(7, client.isAuthorized());
                statement.setString(8, client.getPassportData());
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new ClientDAO.ClientDAOException();
        }
    }

    public static Client getClientByLoginAndPassword(String login, String password) {
        Client client = null;
        try {
            PreparedStatement statement = manager.getConnection().
                    prepareStatement("SELECT * FROM clients WHERE email = ? AND  password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            logger.debug("getWarnings: '" + statement.toString() + "'");
            if (resultSet.next()) {
                client = new Client(resultSet.getString("email"),
                        resultSet.getString("password"));
                logger.debug(client.toString());
            } else {
                logger.debug("client not found");
            }
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            logger.debug(e.getErrorCode() + " " + e.getMessage());
        }

        return client;
    }

    public static class ClientDAOException extends Exception {
        public ClientDAOException() {
        }
    }

}
