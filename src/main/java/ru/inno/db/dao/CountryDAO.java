package ru.inno.db.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private static final Logger logger = LogManager.getLogger(CountryDAO.class);
    private static IConnectionManager manager = ConnectionManager.getInstance();

    public CountryDAO() {
    }

    public static List<Country> getAll() throws CountryDAO.CountryDAOException {
        ArrayList countryList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM countries");
            while (resultSet.next()) {
                Country country = new Country(resultSet.getInt("id"), resultSet.getString("name"));
                countryList.add(country);
            }
            logger.error(countryList);
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new CountryDAO.CountryDAOException();
        }
        return countryList;

    }

    public static class CountryDAOException extends Exception {
        public CountryDAOException() {
        }
    }
}
