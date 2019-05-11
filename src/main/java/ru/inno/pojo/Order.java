package ru.inno.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class Order {
    private int id;
    private Date data;
    private String order_number;
    private Client client;
    private Tour tour;

    public Order(Date data, String order_number, Client client, Tour tour) {
        this.data = data;
        this.order_number = order_number;
        this.client = client;
        this.tour = tour;
    }

    public Order(int id, Date data, String order_number, Client client, Tour tour) {
        this.id = id;
        this.data = data;
        this.order_number = order_number;
        this.client = client;
        this.tour = tour;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Client getClient() {
        return client;
    }

    @XmlElement
    public void setClient(Client client) {
        this.client = client;
    }

    public Tour getTour() {
        return tour;
    }

    @XmlElement
    public void setTour(Tour tour) {
        this.tour = tour;
    }

}
