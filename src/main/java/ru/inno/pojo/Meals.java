package ru.inno.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Питание
 *
 * @author Kosareva Darya
 */

@XmlRootElement(name = "Meals")
public class Meals {
    private int id;
    private String type;

    /**
     * @param type - тип питания
     */
    public Meals(String type) {
        this.type = type;
    }

    public Meals() {
    }

    public Meals(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Meals{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
