package ru.inno.db.marshal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Преобразование объекта в XML и XML в объект
 */
public class MyMarshaller implements Parser {
    private final String FILE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "xml" + File.separator;

    /**
     * Преобразование объекта в XML в файл
     * Указываем в настройке, что XML должен быть отоформатирован
     *
     * @param object - объект, который записываем в file
     * @param index
     * @throws JAXBException
     */

    public void saveObject(Object object, String index) throws JAXBException {
        //System.out.println(object.toString());
        File file = new File(FILE_PATH + object.getClass().getSimpleName() + index + ".xml");
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, file);
    }

    /**
     * Преобразование XML из файла в объект
     *
     * @param myClass - Class объекта, который сериализуем из файла
     * @param index
     * @return object
     * @throws JAXBException
     */

    public Object getObject(Class myClass, String index) throws JAXBException {
        File file = new File(FILE_PATH + myClass.getSimpleName() + index + ".xml");
        JAXBContext context = JAXBContext.newInstance(myClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);
        return object;
    }
}