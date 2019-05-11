package ru.inno.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Пользователи системы
 *
 * @author Kosareva Darya
 */
@XmlRootElement(name = "User")
public class User {
    private int id;
    private String lastName;
    private String firstName;
    private String secondName;
    private boolean isAuthorized;


    /**
     * @param lastName     - фамилия клиента
     * @param firstName    - имя клиента
     * @param secondName   - отчество клиента
     * @param isAuthorized - true, если клиент авторизован, и false, если не авторизован
     */
    public User(String lastName, String firstName, String secondName, boolean isAuthorized) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.isAuthorized = isAuthorized;
    }

    public User(int id, String lastName, String firstName, String secondName, boolean isAuthorized) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.isAuthorized = isAuthorized;
    }

    public User() {
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @XmlElement
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}