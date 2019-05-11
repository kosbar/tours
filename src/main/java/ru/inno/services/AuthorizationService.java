package ru.inno.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.dao.ClientDAO;
import ru.inno.services.interfaceService.IAuthorizationService;

public class AuthorizationService implements IAuthorizationService {
    private static final Logger logger = LogManager.getLogger(AuthorizationService.class);


    public Boolean auth(String login, String password) {
        if (login == null || password == null) {
            logger.info("login/password null");
            return false;
        }
        if (ClientDAO.getClientByLoginAndPassword(login, PasswordEncoder.encode(password)) != null) {
            logger.info("client found");
            return true;
        } else {
            logger.info("client not found");
            return false;
        }
    }
}
