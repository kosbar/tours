
package ru.inno.db.dao;

import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static IConnectionManager manager = ConnectionManager.getInstance();

    public OrderDAO() {
    }

    public static List<Order> getById(int id) throws OrderDAO.OrderDAOException {
        ArrayList ordertList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM order WHERE id=" + id);

/* while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getBoolean("isAuthorized"),
                        resultSet.getDate("dateOfBirth"),
                        resultSet.getString("passportData"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                ordertList.add(order);
            }*/

            return ordertList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderDAO.OrderDAOException();
        }
    }

    /*public static List<Order> getAll() throws OrderDAO.OrderDAOException {
        ArrayList orderList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");

            while (resultSet.next()) {
                int idClient =  resultSet.getInt("idClient");
                int idTour =  resultSet.getInt("idTour");
                ResultSet resultSet_client = statement.executeQuery("SELECT * FROM client WHERE id = " +idClient);
                while (resultSet_client.next()) {
                    Client client = new
                }
                Order order = new Order(
                        resultSet.getDate("data"),
                        resultSet.getString("orderNumber"),
                        new Client(resultSet.getString("type")),
                        new Tour(resultSet.getString("type")));


                orderList.add(order);
            }
        } catch (SQLException e) {
           logger.error((e.getMessage()));
        }

        return orderList;

    }*/

    public static void updateOne(int id, String lastName) throws OrderDAO.OrderDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE order SET lastName= ? WHERE id=?");
            statement.setString(1, lastName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderDAO.OrderDAOException();
        }
    }

    public static void updateAll(String lastName) throws OrderDAO.OrderDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE order SET lastName= ?");
            statement.setString(1, lastName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderDAO.OrderDAOException();
        }
    }

    public static void deleteById(int id) throws OrderDAO.OrderDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM order where id = ? ");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderDAO.OrderDAOException();
        }
    }

    public static class OrderDAOException extends Exception {
        public OrderDAOException() {
        }
    }

   /* public static void insertOne(Order order) throws OrderDAO.OrderDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO order (" +
                            "firstName,lastName, secondName, dateOfBirth, email, password," +
                            " isAuthorized, passportData) VALUES (?, ?, ?, ?,?,?,?,?)");
            statement.setString(1, order.getFirstName());
            statement.setString(2, order.getLastName());
            statement.setString(3, order.getSecondName());
            statement.setDate(4, (Date) order.getDateofBirth());
            statement.setString(5, order.getEmail());
            statement.setString(6, order.getPassword());
            statement.setBoolean(7, order.isAuthorized());
            statement.setString(8, order.getPassportData());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderDAO.OrderDAOException();
        }
    }*/

   /* public static void insertAll(List<Order> orders) throws OrderDAO.OrderDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO order (" +
                            "firstName,lastName, secondName, dateOfBirth, email, password," +
                            " isAuthorized, passportData) VALUES (?, ?, ?, ?,?,?,?,?)");

            for (Order order : orders) {
                statement.setString(1, order.getFirstName());
                statement.setString(2, order.getLastName());
                statement.setString(3, order.getSecondName());
                statement.setDate(4, (Date) order.getDateofBirth());
                statement.setString(5, order.getEmail());
                statement.setString(6, order.getPassword());
                statement.setBoolean(7, order.isAuthorized());
                statement.setString(8, order.getPassportData());
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderDAO.OrderDAOException();
        }
    }*/

}

