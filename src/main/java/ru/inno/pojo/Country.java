package ru.inno.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Directory of countries.
 *
 * @author Kosareva Darya
 */

@XmlRootElement(name = "Country")
public class Country {
    private int id;
    private String name;

    public Country() {
    }

    /**
     * @param name - The name of the country.
     */
    public Country(String name) {
        this.name = name;
    }

    public Country(int id, String name) {
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

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
