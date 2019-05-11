package ru.inno.db.dao;

import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.Hotel;
import ru.inno.pojo.TypeHotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private static IConnectionManager manager = ConnectionManager.getInstance();

    public HotelDAO() {
    }

    public static List<Hotel> getAll() {
        ArrayList hoteltList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM hotels INNER JOIN hoteltypes ON hotels.idTypeHotel = hoteltypes.id");
            while (resultSet.next()) {
                Hotel hotel = new Hotel(
                        resultSet.getInt("star"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        new TypeHotel(resultSet.getString("type")));
                hoteltList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoteltList;

    }

    public static List<Hotel> getById(int id) throws HotelDAO.HotelDAOException {
        ArrayList hoteltList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM hotels INNER JOIN hoteltypes ON hotels.idTypeHotel = hoteltypes.id  " +
                            "WHERE hotel.id=" + id);
            while (resultSet.next()) {
                Hotel hotel = new Hotel(
                        resultSet.getInt("star"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        new TypeHotel(resultSet.getString("type")));
                hoteltList.add(hotel);
            }
            return hoteltList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        }
    }

    public static void updateOne(int id, int star) throws HotelDAO.HotelDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE hotels SET star= ? WHERE id=?");
            statement.setInt(1, star);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        }
    }

    public static void updateAll(int star) throws HotelDAO.HotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE hotels SET star= ?");
            statement.setInt(1, star);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        }
    }

    public static void deleteById(int id) throws HotelDAO.HotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM hotels where id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        }
    }

    public static void deleteAll() throws HotelDAO.HotelDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM hotel");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        }
    }

    public static void insertOne(Hotel hotel) throws HotelDAO.HotelDAOException {

        try {
            HotelType type = HotelType.BIG;
            HotelType.valueOf("BIG");

            String typeHotel = hotel.getTypeHotel().getType();
            int idtype = TypeHotelDAO.getIdByType(typeHotel);
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO hotels (name, address, star,idTypeHotel) VALUES (?,?,?,?)");
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getAddress());
            statement.setInt(3, hotel.getStar());
            statement.setInt(4, idtype);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        } catch (TypeHotelDAO.TypeHotelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insertAll(List<Hotel> hotelList) throws HotelDAO.HotelDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO hotels (name, address, star,idTypeHotel) VALUES (?,?,?,?)");

            for (Hotel hotel : hotelList) {
                String typeHotel = hotel.getTypeHotel().getType();
                int idtype = TypeHotelDAO.getIdByType(typeHotel);
                statement.setString(1, hotel.getName());
                statement.setString(2, hotel.getAddress());
                statement.setInt(3, hotel.getStar());
                statement.setInt(4, idtype);
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new HotelDAO.HotelDAOException();
        } catch (TypeHotelDAO.TypeHotelDAOException e) {
            e.printStackTrace();
        }
    }

    public static class HotelDAOException extends Exception {
        public HotelDAOException() {
        }
    }

}
