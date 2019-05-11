package ru.inno.db.marshal.classCollections;

import ru.inno.pojo.Tour;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Коллекция объектов класса Tour
 */
@XmlRootElement(name = "TourList")
public class TourList {
    private List<Tour> list = new ArrayList();

    public void add(Tour object) {
        this.list.add(object);
    }

    @XmlElementRef
    public List<Tour> getList() {
        return list;
    }

    public void setList(List<Tour> list) {
        this.list = list;
    }
}