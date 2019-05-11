package ru.inno.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.services.TourSelectionService;
import ru.inno.services.interfaceService.ITourSelectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TourSelectionServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(TourSelectionServlet.class);
    private static ITourSelectionService as = new TourSelectionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String message;
        String path;
        logger.info("method ok");

        int idCountry = Integer.parseInt(request.getParameter("countries"));
        String beginningOfPeriod = request.getParameter("beginningOfPeriod");
        String endOfPeriod = request.getParameter("endOfPeriod");
        int idMeals = Integer.parseInt(request.getParameter("meals"));
        int stars = Integer.parseInt(request.getParameter("stars"));
        String flight = request.getParameter("flight");
/*


        if (newInterval.Begin < interval.End && newInterval.End > interval.Begin)
            MessageBox.Show("Intersection!");*/
        logger.debug("country " + flight);

        //int routes = Integer.parseInt(request.getParameter("routes"));
        //int meals = Integer.parseInt(request.getParameter("meals"));




        /*try {
            List<Tour> tours = TourDAO.selectionOfTheTour(routes, meals);
            request.setAttribute("toursAtt", tours);
            logger.info("ok");
        } catch (TourDAO.TourDAOException e) {
            logger.error(e.getMessage());
        }*/


       /* if (as.selectTour(routes, meals)) {
            path = "listTours.jsp";
            message = "Тур найден";
            Servlet.includeTheContentOfTheResourceInResponse(path, message, "green", request, response);
            logger.info("tour find");
        } else {
            path = "firstPage.jsp";
            message = "Ошибка";
            Servlet.includeTheContentOfTheResourceInResponse(path, message, "red", request, response);
            logger.error("find Error");
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        List<Integer> lisy = new ArrayList<Integer>();
        lisy.add(0);
    }


    public long stringParseInMilliseconds(String dateString, String pattern) {//
        long milliseconds;
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        milliseconds = date.getTime();
        return milliseconds;
    }
}
