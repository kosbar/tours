package ru.inno.db.marshal.classCollections;

import ru.inno.pojo.TypeHotel;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Коллекция объектов класса TypeHotel
 */
@XmlRootElement(name = "TypeHotelList")
public class TypeHotelList {
    private List<TypeHotel> list = new ArrayList();

    public void add(TypeHotel object) {
        this.list.add(object);
    }

    @XmlElementRef
    public List<TypeHotel> getList() {
        return list;
    }

    public void setList(List<TypeHotel> list) {
        this.list = list;
    }
}