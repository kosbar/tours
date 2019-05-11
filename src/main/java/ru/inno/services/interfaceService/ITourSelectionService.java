package ru.inno.services.interfaceService;

import ru.inno.pojo.Country;
import ru.inno.pojo.Meals;
import ru.inno.pojo.Tour;

import java.util.List;

public interface ITourSelectionService {


    List<Country> getListCountries();

    List<Meals> getListMeals();

    List<Tour> selectTour(int idCountry, String beginningOfPeriod, String endOfPeriod, int idMeals, int stars, boolean flight);

}
