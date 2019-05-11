package ru.inno.pojo;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement
public class Tour {
    private int id;
    private long beginningOfPeriod;
    private long endOfPeriod;
    private Hotel hotel;
    private Meals meals;
    private long price;
    private long base_price;
    private String tourOperator;
    private boolean isFlightIncluded;
    private String description;

    public Tour(int id, long beginningOfPeriod, long endOfPeriod, Hotel hotel, Meals meals, long price, long base_price, String tourOperator, boolean isFlightIncluded, String description) {
        this.id = id;
        this.beginningOfPeriod = beginningOfPeriod;
        this.endOfPeriod = endOfPeriod;
        this.hotel = hotel;
        this.meals = meals;
        this.price = price;
        this.base_price = base_price;
        this.tourOperator = tourOperator;
        this.isFlightIncluded = isFlightIncluded;
        this.description = description;
    }

    public Tour(long beginningOfPeriod, long endOfPeriod, Hotel hotel, Meals meals, long price, String tourOperator, boolean isFlightIncluded) {

        this.beginningOfPeriod = beginningOfPeriod;
        this.endOfPeriod = endOfPeriod;
        this.hotel = hotel;
        this.meals = meals;
        this.price = price;
        this.tourOperator = tourOperator;
        this.isFlightIncluded = isFlightIncluded;

    }

    public Tour(String description) {
        this.description = description;
    }

    public Tour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long calculateCostOfTour(int rateMeals) {//расчитать стоимость тура
        this.price = this.base_price * rateMeals;
        return this.price;
    }


    public long getBeginningOfPeriod() {
        return beginningOfPeriod;
    }

    @XmlElement
    public void setBeginningOfPeriod(long beginningOfPeriod) {
        this.beginningOfPeriod = beginningOfPeriod;
    }

    public long getEndOfPeriod() {
        return endOfPeriod;
    }

    @XmlElement
    public void setEndOfPeriod(long endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
    }


    public Hotel getHotel() {
        return hotel;
    }

    @XmlElement
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Meals getMeals() {
        return meals;
    }

    @XmlElement
    public void setMeals(Meals meals) {
        this.meals = meals;
    }

    public long getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(long price) {
        this.price = price;
    }

    public String getTourOperator() {
        return tourOperator;
    }

    @XmlElement
    public void setTourOperator(String tourOperator) {
        this.tourOperator = tourOperator;
    }

    public boolean isFlightIncluded() {
        return isFlightIncluded;
    }

    @XmlElement
    public void setFlightIncluded(boolean isFlightIncluded) {
        isFlightIncluded = isFlightIncluded;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", beginningOfPeriod=" + beginningOfPeriod +
                ", endOfPeriod=" + endOfPeriod +
                ", hotel=" + hotel +
                ", meals=" + meals +
                ", price=" + price +
                ", base_price=" + base_price +
                ", tourOperator=" + tourOperator +
                ", isFlightIncluded=" + isFlightIncluded +
                ", description='" + description + '\'' +
                '}';
    }
}
