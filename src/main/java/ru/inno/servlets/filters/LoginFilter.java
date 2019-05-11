package ru.inno.servlets.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    //private FilterConfig filterConfig;
    private static final Logger logger = LogManager.getLogger(LoginFilter.class);


    public void init(FilterConfig filterConfig) {
        // this.filterConfig = filterConfig;
        logger.info("ok");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        logger.info("ok");
        Boolean isAuth = (Boolean) ((HttpServletRequest) request).getSession().
                getAttribute("isAuth");

        if (isAuth != null && isAuth) {
            try {
                logger.info("chain.doFilter");
                chain.doFilter(request, response);
                //return;
            } catch (IOException e) {
                logger.error(e.getMessage());
            } catch (ServletException e) {
                logger.error(e.getMessage());
            }

        } else {
            try {
                ((HttpServletResponse) response).sendRedirect("/tour");
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                logger.error("error, isAuth=" + isAuth);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }


    }


    public void destroy() {
        //this.filterConfig = null;
        logger.info("ok");
    }
}
