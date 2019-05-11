package ru.inno.db.marshal.classCollections;

import ru.inno.pojo.Meals;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Коллекция объектов класса Meals
 */
@XmlRootElement(name = "MealsList")
public class MealsList {
    private List<Meals> list = new ArrayList();

    public void add(Meals object) {
        this.list.add(object);
    }

    @XmlElementRef
    public List<Meals> getList() {
        return list;
    }

    public void setList(List<Meals> list) {
        this.list = list;
    }


}