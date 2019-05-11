package ru.inno.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.dao.CountryDAO;
import ru.inno.db.dao.MealsDAO;
import ru.inno.pojo.Country;
import ru.inno.pojo.Meals;
import ru.inno.services.AuthorizationService;
import ru.inno.services.interfaceService.IAuthorizationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthorizationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AuthorizationServlet.class);
    private static IAuthorizationService as = new AuthorizationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String message;
        String path;
        logger.info("method ok");

        try {
            List<Country> countries = CountryDAO.getAll();
            request.setAttribute("countriesAtt", countries);
            List<Meals> meals = MealsDAO.getAll();
            request.setAttribute("mealsAtt", meals);
        } catch (CountryDAO.CountryDAOException e) {
            logger.error(e.getMessage());
        } catch (MealsDAO.MealsDAOException e) {
            logger.error(e.getMessage());
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("") || password.equals("")) {
            path = "index.jsp";
            message = "Заполните все поля";
            logger.error("null fields");
            Servlet.includeTheContentOfTheResourceInResponse(path, message, "red", request, response);
        } else {
            if (as.auth(login, password)) {
                request.getSession().setAttribute("isAuth", true);
                path = "firstPage.jsp";
                message = "Авторизация прошла успешно";
                //redirect
               /* try {
                    ((HttpServletResponse) response).sendRedirect("/tourSelect");
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }*/
                Servlet.includeTheContentOfTheResourceInResponse(path, message, "green", request, response);
                logger.info("client authorized");
            } else {
                path = "index.jsp";
                message = "Логин или пароль введены неверно";
                logger.error("Authorisation Error");
                Servlet.includeTheContentOfTheResourceInResponse(path, message, "red", request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {


       /* int selectedOption = -1;
        try {
            //selectedOption = Integer.parseInt(request.getParameter("countries"));
            String sselectedOption = req.getParameter("country");
            logger.debug("selectedOption " + sselectedOption);
        } catch (Exception e) {
            logger.debug("666");
        }

        if (selectedOption != -1) {
            // получаем запись из БД по selectedOption
            List<City> cities = null;
            try {
                cities = CityDAO.getById(selectedOption);
            } catch (CityDAO.CityDAOException e) {
                logger.error(e.getMessage());
            }
            req.setAttribute("citiesAtt", cities);
            logger.debug("44444");
        } else {
            // че-то не так
            logger.debug("456677");
        }
*/

    }
}
