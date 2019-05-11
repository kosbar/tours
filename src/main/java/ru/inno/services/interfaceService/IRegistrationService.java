package ru.inno.services.interfaceService;

import ru.inno.db.dao.ClientDAO;

public interface IRegistrationService {
    int regUser(String lastName, String firstName, String secondName,
                boolean isAuthorized, long dateOfBirth, String passportData,
                String email, String password) throws ClientDAO.ClientDAOException;
}
