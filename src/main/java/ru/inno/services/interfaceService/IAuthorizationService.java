package ru.inno.services.interfaceService;

public interface IAuthorizationService {
    Boolean auth(String login, String password);
}
