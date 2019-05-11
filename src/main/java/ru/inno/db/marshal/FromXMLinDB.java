package ru.inno.db.marshal;


import ru.inno.db.dao.ClientDAO;
import ru.inno.db.dao.HotelDAO;
import ru.inno.db.dao.MealsDAO;
import ru.inno.db.dao.TypeHotelDAO;
import ru.inno.db.marshal.classCollections.ClientList;
import ru.inno.db.marshal.classCollections.HotelList;
import ru.inno.db.marshal.classCollections.MealsList;
import ru.inno.db.marshal.classCollections.TypeHotelList;

import javax.xml.bind.JAXBException;

public class FromXMLinDB {
    public static void mealsFromXMLinDB() {
        try {
            Parser parser = new MyMarshaller();
            MealsList meals = (MealsList) parser.getObject(MealsList.class, "");

            MealsDAO.insertAll(meals.getList());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (MealsDAO.MealsDAOException e) {
            e.printStackTrace();
        }
    }

    public static void clientFromXMLinDB() {
        try {
            Parser parser = new MyMarshaller();
            ClientList client = (ClientList) parser.getObject(ClientList.class, "");

            ClientDAO.insertAll(client.getList());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ClientDAO.ClientDAOException e) {
            e.printStackTrace();
        }
    }

    public static void hotelFromXMLinDB() {
        try {
            Parser parser = new MyMarshaller();
            HotelList hotel = (HotelList) parser.getObject(HotelList.class, "");

            HotelDAO.insertAll(hotel.getList());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (HotelDAO.HotelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void typeHotelFromXMLinDB() {
        try {
            Parser parser = new MyMarshaller();
            TypeHotelList typeHotel = (TypeHotelList) parser.getObject(TypeHotelList.class, "");

            TypeHotelDAO.insertAll(typeHotel.getList());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TypeHotelDAO.TypeHotelDAOException e) {
            e.printStackTrace();
        }
    }
   /* public static void orderFromXMLinDB() {
       try {
            Parser parser = new MyMarshaller();
            OrderList order = (OrderList) parser.getObject(OrderList.class, "");

            OrderDAO.insertAll(order.getList());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (OrderDAO.OrderDAOException e) {
            e.printStackTrace();
        }
    }*/

   /* public static void tourFromXMLinDB() {
       try {
            Parser parser = new MyMarshaller();
            TourList tour = (TourList) parser.getObject(TourList.class, "");

            TourDAO.insertAll(tour.getList());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TourDAO.TourDAOException e) {
            e.printStackTrace();
        }
    }*/
}
