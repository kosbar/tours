package ru.inno.db.marshal.classCollections;

import ru.inno.pojo.Hotel;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Коллекция объектов класса Hotel
 */
@XmlRootElement(name = "HotelList")
public class HotelList {
    private List<Hotel> list = new ArrayList();

    public void add(Hotel object) {
        this.list.add(object);
    }

    @XmlElementRef
    public List<Hotel> getList() {
        return list;
    }

    public void setList(List<Hotel> list) {
        this.list = list;
    }
}