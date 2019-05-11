package ru.inno.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Тип гостиницы (хостел, апартаменты и др)
 */
@XmlRootElement
public class TypeHotel {
    private int id;
    private String type;

    /**
     * @param type - тип гостиницы
     */
    public TypeHotel(String type) {
        this.type = type;
    }

    public TypeHotel(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public TypeHotel() {
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
        return "TypeHotel{" +
                "type='" + type + '\'' +
                '}';
    }
}
