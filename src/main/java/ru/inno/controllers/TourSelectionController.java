package ru.inno.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.services.TourSelectionService;

@Controller
public class TourSelectionController {

    private static Logger logger = LogManager.getLogger(TourSelectionController.class);
    @Autowired
    TourSelectionService tourSelectionService;//inject by setter or Constructor

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getRequest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("countriesAtt", tourSelectionService.getListCountries());
        modelAndView.addObject("mealsAtt", tourSelectionService.getListMeals());
        logger.info("ListCountries and listMeals uploaded");
        return modelAndView;
    }

    //TourSelectionController.postRequest
    @RequestMapping(value = "/tourSelect", method = RequestMethod.POST)
    public ModelAndView postRequest(
            @RequestParam("countries") String idCountry,
            @RequestParam("beginningOfPeriod") String beginningOfPeriod,
            @RequestParam("endOfPeriod") String endOfPeriod,
            @RequestParam("meals") String idMeals,
            @RequestParam("stars") String stars,
            @RequestParam(value = "flight", required = false) boolean flight) {
        //@RequestParam(value = "flight", required = false ) boolean flight) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listTours");
        modelAndView.addObject("beginningOfPeriodInfo", beginningOfPeriod);
        modelAndView.addObject("endOfPeriodInfo", endOfPeriod);
        modelAndView.addObject("country", idCountry);
        modelAndView.addObject("toursAtt", tourSelectionService.selectTour(
                "".equals(idCountry) ? 0 : Integer.parseInt(idCountry), beginningOfPeriod, endOfPeriod,
                "".equals(idMeals) ? 0 : Integer.parseInt(idMeals), "".equals(stars) ? 0 : Integer.parseInt(stars),
                flight));
        logger.info("Tour selected");
        return modelAndView;
    }


}
