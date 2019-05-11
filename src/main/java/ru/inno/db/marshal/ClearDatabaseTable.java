package ru.inno.db.marshal;

import ru.inno.db.dao.ClientDAO;
import ru.inno.db.dao.HotelDAO;
import ru.inno.db.dao.MealsDAO;
import ru.inno.db.dao.TypeHotelDAO;

public class ClearDatabaseTable {
    public static void mealsDelete() {
        try {
            MealsDAO.deleteAll();
        } catch (MealsDAO.MealsDAOException e) {
            e.printStackTrace();
        }
    }

    public static void clientDelete() {
        try {
            ClientDAO.deleteAll();
        } catch (ClientDAO.ClientDAOException e) {
            e.printStackTrace();
        }
    }

    public static void hotelDelete() {
        try {
            HotelDAO.deleteAll();
        } catch (HotelDAO.HotelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void typeTypeHotelDelete() {
        try {
            TypeHotelDAO.deleteAll();
        } catch (TypeHotelDAO.TypeHotelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void orderDelete() {
      /*  try {
            OrderDAO.deleteAll();
        } catch (OrderDAO.OrderDAOException e) {
            e.printStackTrace();
        }*/
    }

    public static void tourDelete() {
     /*   try {
            TourDAO.deleteAll();
        } catch (TourDAO.TourDAOException e) {
            e.printStackTrace();
        }*/
    }
}
