package ru.inno.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.dao.ClientDAO;
import ru.inno.services.RegistrationService;
import ru.inno.services.Validation;
import ru.inno.services.interfaceService.IRegistrationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);
    private static IRegistrationService as = new RegistrationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String message;
        String path;

        Boolean isAuthorized = true;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String secondName = request.getParameter("secondName");
        String passportData = request.getParameter("passportData");

        String dateOfBirth = request.getParameter("dateOfBirth");

        if (login.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") ||
                secondName.equals("") || passportData.equals("") || dateOfBirth.trim().equals("")) {
            path = "reg.jsp";
            message = "Заполните все поля";
            logger.error("null fields");
            Servlet.includeTheContentOfTheResourceInResponse(path, message, "red", request, response);
        } else {
            int result = 0;
            try {
                result = as.regUser(lastName, firstName, secondName, isAuthorized,
                        Validation.stringParseInMilliseconds(dateOfBirth, "yyyy-MM-dd"), passportData, login, password);
            } catch (ClientDAO.ClientDAOException e) {
                logger.error(e.getMessage());
            }
            if (result == 0) {
                path = "index.jsp";
                message = "Регистрация прошла успешно";
                Servlet.includeTheContentOfTheResourceInResponse(path, message, "green", request, response);
                logger.info("OK registration");
            } else {
                if (result == 1062) {
                    message = "Пользователь с таким email уже зарегистрирован";
                } else {
                    message = "При регистрации возникла ошибка";
                }
                path = "reg.jsp";
                Servlet.includeTheContentOfTheResourceInResponse(path, message, "red", request, response);
                logger.info("Error registration");
            }

        }
    }

}
