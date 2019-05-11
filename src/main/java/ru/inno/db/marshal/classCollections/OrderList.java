package ru.inno.db.marshal.classCollections;

import ru.inno.pojo.Order;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Коллекция объектов класса Order
 */
@XmlRootElement(name = "OrderList")
public class OrderList {
    private List<Order> list = new ArrayList();

    public void add(Order object) {
        this.list.add(object);
    }

    @XmlElementRef
    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }
}