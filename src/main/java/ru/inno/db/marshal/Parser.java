package ru.inno.db.marshal;


import javax.xml.bind.JAXBException;

public interface Parser {
    Object getObject(Class myClass, String index) throws JAXBException;

    void saveObject(Object object, String index) throws JAXBException;


}