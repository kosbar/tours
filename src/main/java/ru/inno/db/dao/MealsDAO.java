package ru.inno.db.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.Meals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MealsDAO {
    private static final Logger logger = LogManager.getLogger(MealsDAO.class);
    private static IConnectionManager manager = ConnectionManager.getInstance();

    public MealsDAO() {
    }

    public static List<Meals> getAll() throws MealsDAO.MealsDAOException {
        ArrayList mealsList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM meals");
            while (resultSet.next()) {
                Meals meals = new Meals(resultSet.getInt("id"), resultSet.getString("type"));
                mealsList.add(meals);
            }
            logger.error(mealsList);
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
        logger.error(mealsList);
        return mealsList;

    }

    public static List<Meals> getById(int id) throws MealsDAO.MealsDAOException {
        ArrayList mealstList = new ArrayList();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM meals WHERE id=" + id);
            while (resultSet.next()) {
                Meals meals = new Meals(resultSet.getString("type"));
                mealstList.add(meals);
            }
            return mealstList;
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static void updateOne(int id, String type) throws MealsDAO.MealsDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE meals SET type= ? WHERE id=?");
            statement.setString(1, type);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static void updateAll(String type) throws MealsDAO.MealsDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "UPDATE meals SET type= ?");
            statement.setString(1, type);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static void deleteById(int id) throws MealsDAO.MealsDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM meals where id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static void deleteAll() throws MealsDAO.MealsDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "DELETE FROM meals  ");
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static void insertOne(Meals meals) throws MealsDAO.MealsDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO meals (type) VALUES (?)");
            statement.setString(1, meals.getType());

            statement.execute();
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static void insertAll(List<Meals> mealsList) throws MealsDAO.MealsDAOException {

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO meals (type) VALUES (?)");

            for (Meals meals : mealsList) {
                statement.setString(1, meals.getType());
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new MealsDAO.MealsDAOException();
        }
    }

    public static class MealsDAOException extends Exception {
        public MealsDAOException() {
        }
    }

}
