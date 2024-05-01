package com.typecasters.apitowerofwords;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FieldConverter implements AttributeConverter<String, String> {

    private static final String ENCRYPTION_PASSWORD = "thetrigger"; // Change this to your actual encryption password

    private static final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    static {
        encryptor.setPassword(ENCRYPTION_PASSWORD);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }
}