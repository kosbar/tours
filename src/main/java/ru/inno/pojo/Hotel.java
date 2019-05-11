package ru.inno.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Гостиницы
 *
 * @author Kosareva Darya
 */
@XmlRootElement
public class Hotel {
    private int id;
    private int star;
    private String name;
    private String address;
    private TypeHotel typeHotel;

    /**
     * @param star      - звездная система гостиниц (классификация зависит от стоимости и качества услуг) [от 1 до 5] звезд
     * @param name      - полное наименование гостиницы
     * @param address   - адрес, где находится гостиница. Страна, город, улица, дом.
     * @param typeHotel - тип гостиницы
     */
    public Hotel(int star, String name, String address, TypeHotel typeHotel) {
        this.star = star;
        this.name = name;
        this.address = address;
        this.typeHotel = typeHotel;
    }

    public Hotel(int id, int star, String name) {
        this.id = id;
        this.star = star;
        this.name = name;
    }

    public Hotel() {
    }

    public Hotel(int id, int star, String name, String address, TypeHotel typeHotel) {
        this.id = id;
        this.star = star;
        this.name = name;
        this.address = address;
        this.typeHotel = typeHotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    @XmlElement
    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public TypeHotel getTypeHotel() {
        return typeHotel;
    }

    @XmlElement
    public void setTypeHotel(TypeHotel typeHotel) {
        this.typeHotel = typeHotel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        return getName().equals(hotel.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "star=" + star +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type=" + typeHotel +
                '}';
    }
}
