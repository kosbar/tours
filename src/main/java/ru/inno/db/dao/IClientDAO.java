package ru.inno.db.dao;

import ru.inno.pojo.Client;

public interface IClientDAO {
    Client insertOne(String login, String password);

    Client getClientByLoginAndPassword(String login, String password);
}

