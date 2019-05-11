package ru.inno.db.marshal.classCollections;

import ru.inno.pojo.Client;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Коллекция объектов класса Client
 */
@XmlRootElement(name = "MealsList")
public class ClientList {
    private List<Client> list = new ArrayList();

    public void add(Client object) {
        this.list.add(object);
    }

    @XmlElementRef
    public List<Client> getList() {
        return list;
    }

    public void setList(List<Client> list) {
        this.list = list;
    }
}