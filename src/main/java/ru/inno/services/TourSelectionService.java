package ru.inno.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.inno.db.dao.CountryDAO;
import ru.inno.db.dao.MealsDAO;
import ru.inno.db.dao.TourDAO;
import ru.inno.pojo.Country;
import ru.inno.pojo.Meals;
import ru.inno.pojo.Tour;
import ru.inno.services.interfaceService.ITourSelectionService;

import java.util.ArrayList;
import java.util.List;

@Component
public class TourSelectionService implements ITourSelectionService {

    private static final Logger logger = LogManager.getLogger(TourSelectionService.class);


    public TourSelectionService() {
    }


    public List<Country> getListCountries() {
        List<Country> countries = null;
        try {
            countries = CountryDAO.getAll();
            logger.debug(countries);
        } catch (CountryDAO.CountryDAOException e) {
            logger.error(e.getMessage());
        }
        return countries;
    }


    public List<Meals> getListMeals() {
        List<Meals> meals = null;
        try {
            meals = MealsDAO.getAll();
            logger.debug(meals);
        } catch (MealsDAO.MealsDAOException e) {
            logger.error(e.getMessage());
        }
        return meals;
    }

    public List<Tour> selectTour(int idCountry, String beginningOfPeriod, String endOfPeriod, int idMeals,
                                 int stars, boolean flight) {
        List<Tour> tours = new ArrayList();
        // new java.util.Date();
        try {
            tours = TourDAO.selectionOfTheTour(idCountry, beginningOfPeriod, endOfPeriod, idMeals, stars, flight);
            if (tours.size() != 0) {
                logger.info("Tour found " + tours);
            } else {
                logger.info("Tour not found");
            }
        } catch (TourDAO.TourDAOException e) {
            logger.error(e.getMessage());
        }
        return tours;
    }
}
