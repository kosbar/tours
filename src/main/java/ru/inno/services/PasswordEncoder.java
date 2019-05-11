package ru.inno.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger logger = LogManager.getLogger(PasswordEncoder.class);

    public static String md5(String password) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = password.getBytes();
            byte[] arraySecond = md.digest(array);
            result = new String(arraySecond);
            logger.info("md5 ok");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        logger.info("md5 ok");
        return result;
    }

    public static String encode(String password) {
        String result = md5(password) + "dkaknkykak";
        StringBuilder a = new StringBuilder(result).reverse();
        result = a.toString();
        result = md5(result);
        logger.info("encode");
        return result;
    }

}
