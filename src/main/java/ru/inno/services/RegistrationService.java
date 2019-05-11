package ru.inno.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inno.db.dao.ClientDAO;
import ru.inno.pojo.Client;
import ru.inno.services.interfaceService.IRegistrationService;

public class RegistrationService implements IRegistrationService {
    private static final Logger logger = LogManager.getLogger(RegistrationService.class);
    private static ClientDAO clientDAO = new ClientDAO();

    public int regUser(String lastName, String firstName, String secondName,
                       boolean isAuthorized, long dateOfBirth, String passportData,
                       String login, String password) {
        if (login == null || password == null || lastName == null || firstName == null ||
                secondName == null || dateOfBirth == 0l || passportData == null) {
            logger.error("result=999 Not all fields are filled out");
            return 999;
        }
        int result = ClientDAO.insertOne(new Client(lastName, firstName, secondName,
                isAuthorized, dateOfBirth, passportData,
                login, PasswordEncoder.encode(password)));
        logger.error("result= " + result);
        return result;
    }
}
