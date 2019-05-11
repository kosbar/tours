package ru.inno.db.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.ConnectionManager;
import ru.inno.db.IConnectionManager;
import ru.inno.pojo.Hotel;
import ru.inno.pojo.Meals;
import ru.inno.pojo.Tour;
import ru.inno.pojo.TypeHotel;
import ru.inno.services.Validation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TourDAO {

    private static final Logger logger = LogManager.getLogger(TourDAO.class);
    private static IConnectionManager manager = ConnectionManager.getInstance();

    public TourDAO() {
    }

    public static List<Tour> selectionOfTheTour(int idCountry, String beginningOfPeriod, String endOfPeriod, int idMeals,
                                                int stars, boolean flight) throws TourDAO.TourDAOException {
        ArrayList tourList = new ArrayList();
        String selectString = "";
        ArrayList<String> paramWhere = new ArrayList<String>();
        if (idCountry != 0) {
            paramWhere.add("countries.id=" + idCountry);
        }
        if (idMeals != 0) {
            paramWhere.add("tours.idMeals=" + idMeals);
        }
        if (!"".equals(beginningOfPeriod) && !"".equals(endOfPeriod)) {
            paramWhere.add("tours.beginningOfPeriod<='" + beginningOfPeriod + "'");
            paramWhere.add("tours.endOfPeriod>='" + endOfPeriod + "'");
        }
        if (stars != 0) {
            paramWhere.add("hotels.stars=" + stars);
        }

        if (flight) {
            paramWhere.add("tours.isFlightIncluded=1");
        } else {
            paramWhere.add("tours.isFlightIncluded=0");
        }
        ListIterator<String> litr = paramWhere.listIterator();
        String separator = "";

        while (litr.hasNext()) {
            if (litr.nextIndex() == paramWhere.size() - 1) {
                separator = "";
            } else {
                separator = " AND ";
            }
            selectString += litr.next() + separator;
        }

        try {

            PreparedStatement statement = manager.getConnection().
                    prepareStatement("SELECT\n" +
                            "  tours.id AS idTour,\n" +
                            "  tours.beginningOfPeriod,\n" +
                            "  tours.endOfPeriod,\n" +
                            "  hotels.id AS idHotel,\n" +
                            "  hotels.address,\n" +
                            "  hotels.name AS nameHotel,\n" +
                            "  hotels.star,\n" +
                            "  meals.id AS idMeals,\n" +
                            "  meals.type,\n" +
                            "  tours.price,\n" +
                            "  tours.tourOperator,\n" +
                            "  tours.isFlightIncluded,\n" +
                            "  tours.description,\n" +
                            "  cities.name AS city,\n" +
                            "  countries.name AS country\n" +
                            "FROM (((tours INNER JOIN hotels ON tours.idHotel = hotels.id)\n" +
                            "  INNER JOIN meals ON meals.id = tours.idMeals)\n" +
                            "  INNER JOIN cities\n" +
                            "    ON cities.id = hotels.idCity) " +
                            "INNER JOIN countries ON countries.id=cities.idCountry \n" +
                            "WHERE " + selectString);
            ResultSet resultSet = statement.executeQuery();

            logger.debug("getWarnings3: '" + statement.toString() + "'");
            if (resultSet.next()) {
                Tour tour = new Tour(
                        resultSet.getInt("idTour"),
                        Validation.stringParseInMilliseconds(resultSet.getString("beginningOfPeriod"), "yyyy-MM-dd"),
                        Validation.stringParseInMilliseconds(resultSet.getString("endOfPeriod"), "yyyy-MM-dd"),
                        new Hotel(resultSet.getInt("idHotel"), resultSet.getInt("star"), resultSet.getString("nameHotel"),
                                resultSet.getString("address"), new TypeHotel()),
                        new Meals(resultSet.getInt("idMeals"), resultSet.getString("type")),
                        resultSet.getLong("price"),
                        resultSet.getLong("price"),
                        resultSet.getString("tourOperator"),
                        resultSet.getBoolean("isFlightIncluded"),
                        resultSet.getString("description")
                );
                tourList.add(tour);
            }
            logger.debug("getWarnings6: '" + statement.toString() + "'");

            logger.info(tourList);
        } catch (SQLException e) {
            logger.error((e.getMessage()));
            throw new TourDAO.TourDAOException();
        }
        return tourList;


    }

    public static class TourDAOException extends Exception {
        public TourDAOException() {
        }
    }

}
