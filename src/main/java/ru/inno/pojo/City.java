package ru.inno.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Directory of cities.
 *
 * @author Kosareva Darya
 */

@XmlRootElement(name = "City")
public class City {
    private int id;
    private String name;
    private Country country;

    public City() {
    }

    /**
     * @param name - The name of the city.
     */
    public City(String name) {
        this.name = name;
    }

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
