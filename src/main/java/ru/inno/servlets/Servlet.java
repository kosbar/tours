package ru.inno.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet {
    private static final Logger logger = LogManager.getLogger(AuthorizationServlet.class);

    /**
     * Includes the content of a resource (servlet, JSP page,
     * HTML file) in the response.
     */
    public static void includeTheContentOfTheResourceInResponse(String path, String message, String color, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println("<font color=" + color + ">" + message + "</font>");
            logger.info("getWriter ok");
            request.getRequestDispatcher(path).include(request, response);
        } catch (ServletException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
