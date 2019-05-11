package ru.inno.db.marshal;


import ru.inno.db.dao.HotelDAO;
import ru.inno.db.dao.MealsDAO;
import ru.inno.db.dao.TypeHotelDAO;
import ru.inno.db.marshal.classCollections.HotelList;
import ru.inno.db.marshal.classCollections.MealsList;
import ru.inno.db.marshal.classCollections.TypeHotelList;
import ru.inno.pojo.Hotel;
import ru.inno.pojo.Meals;
import ru.inno.pojo.TypeHotel;

import javax.xml.bind.JAXBException;
import java.util.Iterator;
import java.util.List;

public class FromDBinXMl {
    public static void mealsFromDBinXMl() {

        try {
            List<Meals> mealsList = MealsDAO.getAll();
            Iterator<Meals> it = mealsList.iterator();
            MealsList classList = new MealsList();
            Parser parser = new MyMarshaller();
            while (it.hasNext()) {
                classList.add(it.next());
            }
            parser.saveObject(classList, "");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (MealsDAO.MealsDAOException e) {
            e.printStackTrace();
        }
    }

    public static void hotelFromDBinXMl() {

        try {
            List<Hotel> hotelList = HotelDAO.getAll();
            Iterator<Hotel> it = hotelList.iterator();
            HotelList classList = new HotelList();
            Parser parser = new MyMarshaller();
            while (it.hasNext()) {
                classList.add(it.next());
            }
            parser.saveObject(classList, "");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeTypeHotelFromDBinXMl() {

        try {
            List<TypeHotel> typeTypeHotelList = TypeHotelDAO.getAll();
            Iterator<TypeHotel> it = typeTypeHotelList.iterator();
            TypeHotelList classList = new TypeHotelList();
            Parser parser = new MyMarshaller();
            while (it.hasNext()) {
                classList.add(it.next());
            }
            parser.saveObject(classList, "");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void orderFromDBinXMl() {

     /*   try {
            List<Order> orderList = OrderDAO.getAll();
            Iterator<Order> it = orderList.iterator();
            OrderList classList = new OrderList();
            Parser parser = new MyMarshaller();
            while (it.hasNext()) {
                classList.add(it.next());
            }
            parser.saveObject(classList, "");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (OrderDAO.OrderDAOException e) {
            e.printStackTrace();
        }*/
    }

    public static void tourFromDBinXMl() {

       /* try {
            List<Tour> tourList = TourDAO.getAll();
            Iterator<Tour> it = tourList.iterator();
            TourList classList = new TourList();
            Parser parser = new MyMarshaller();
            while (it.hasNext()) {
                classList.add(it.next());
            }
            parser.saveObject(classList, "");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TourDAO.TourDAOException e) {
            e.printStackTrace();
        }*/
    }

}
