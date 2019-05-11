package ru.inno.db.dao;

import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.TypeHotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeHotelDAO {
    private static IConnectionManager manager = ConnectionManager.getInstance();

    public TypeHotelDAO() {
    }

    public static List<TypeHotel> getAll() {
        ArrayList typeHoteltList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM typeHotel");
            while (resultSet.next()) {
                TypeHotel typeHotel = new TypeHotel(resultSet.getString("type"));
                typeHoteltList.add(typeHotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeHoteltList;

    }

    public static List<TypeHotel> getById(int id) throws TypeHotelDAO.TypeHotelDAOException {
        ArrayList typeHoteltList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM hoteltypes WHERE id=" + id);
            while (resultSet.next()) {
                TypeHotel typeHotel = new TypeHotel(resultSet.getString("type"));
                typeHoteltList.add(typeHotel);
            }
            return typeHoteltList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static int getIdByType(String type) throws TypeHotelDAO.TypeHotelDAOException {

        try {
            int idType = 0;
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM hoteltypes WHERE type='" + type + "'");
            int count = 0;

            while (resultSet.next()) {
                idType = resultSet.getInt("id");
            }

            return idType;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static void updateOne(int id, String type) throws TypeHotelDAO.TypeHotelDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE hoteltypes SET type= ? WHERE id=?");
            statement.setString(1, type);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static void updateAll(String type) throws TypeHotelDAO.TypeHotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE hoteltypes SET type= ?");
            statement.setString(1, type);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static void deleteById(int id) throws TypeHotelDAO.TypeHotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM hoteltypes where id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static void deleteAll() throws TypeHotelDAO.TypeHotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM route  ");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static void insertOne(TypeHotel typeHotel) throws TypeHotelDAO.TypeHotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO hoteltypes (type) VALUES (?)");
            statement.setString(1, typeHotel.getType());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static void insertAll(List<TypeHotel> typeHotelList) throws TypeHotelDAO.TypeHotelDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO hoteltypes (type) VALUES (?)");

            for (TypeHotel typeHotel : typeHotelList) {
                statement.setString(1, typeHotel.getType());
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TypeHotelDAO.TypeHotelDAOException();
        }
    }

    public static class TypeHotelDAOException extends Exception {
        public TypeHotelDAOException() {
        }
    }


}
