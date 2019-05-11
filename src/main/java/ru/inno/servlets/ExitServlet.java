package ru.inno.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AuthorizationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("isAuth", null);
        Servlet.includeTheContentOfTheResourceInResponse("index.jsp", "Вы вышли", "red", request, response);
        logger.info("EXIT");
    }
}