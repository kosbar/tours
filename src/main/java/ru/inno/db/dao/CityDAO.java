package ru.inno.db.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);
    private static IConnectionManager manager = ConnectionManager.getInstance();


    public CityDAO() {
    }

    public static List<City> getAll() throws CityDAO.CityDAOException {
        ArrayList cityList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM cities");
            while (resultSet.next()) {
                City city = new City(resultSet.getInt("id"), resultSet.getString("name"));
                cityList.add(city);
            }
            logger.error(cityList);
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new CityDAO.CityDAOException();
        }
        logger.error(cityList);
        return cityList;
    }

    public static List<City> getById(int id) throws CityDAO.CityDAOException {
        ArrayList cityList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM cities WHERE id = " + id);
            while (resultSet.next()) {
                City city = new City(resultSet.getInt("id"), resultSet.getString("name"));
                cityList.add(city);
            }
            logger.error(cityList);
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new CityDAO.CityDAOException();
        }
        logger.error(cityList);
        return cityList;
    }

    public static class CityDAOException extends Exception {
        public CityDAOException() {
        }
    }
}
