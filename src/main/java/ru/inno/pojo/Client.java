package ru.inno.pojo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Зарегистрированные пользователи в системе
 *
 * @author Kosareva Darya
 */
@XmlRootElement
public class Client extends User {
    // private LocalDate dateOfBirth;
    private int id;
    private long dateOfBirth;
    private String passportData;//??
    private String email;
    private String password;

    /**
     * @param lastName     - фамилия клиента
     * @param firstName    - имя клиента
     * @param secondName   - отчество клиента
     * @param isAuthorized - true, если клиент авторизован, и false, если не авторизован
     * @param dateOfBirth  - дата рождения клиента
     * @param passportData - паспорные данные клиента
     * @param email        - email клиента, так же он является логином для входа в систему
     * @param password     - пароль клиента
     */
    public Client(String lastName, String firstName, String secondName,
                  boolean isAuthorized, long dateOfBirth, String passportData,
                  String email, String password) {
        super(lastName, firstName, secondName, isAuthorized);
        this.dateOfBirth = dateOfBirth;
        this.passportData = passportData;
        this.email = email;
        this.password = password;
    }

    public Client(int id, String lastName, String firstName, String secondName, boolean isAuthorized, int id1, long dateOfBirth, String passportData, String email, String password) {
        super(id, lastName, firstName, secondName, isAuthorized);
        this.id = id1;
        this.dateOfBirth = dateOfBirth;
        this.passportData = passportData;
        this.email = email;
        this.password = password;
    }

    public Client() {
    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassportData() {
        return passportData;
    }

    @XmlElement
    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getDateOfBirth() != client.getDateOfBirth()) return false;
        if (getPassportData() != null ? !getPassportData().equals(client.getPassportData()) : client.getPassportData() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(client.getEmail()) : client.getEmail() != null) return false;
        return getPassword() != null ? getPassword().equals(client.getPassword()) : client.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getDateOfBirth() ^ (getDateOfBirth() >>> 32));
        result = 31 * result + (getPassportData() != null ? getPassportData().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "dateOfBirth=" + dateOfBirth +
                ", passportData='" + passportData + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

