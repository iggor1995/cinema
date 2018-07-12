package com.epam.igor.converter;

import com.epam.igor.entity.Rate;

import javax.persistence.AttributeConverter;

public class RateConverter implements AttributeConverter<Rate, String> {

    @Override
    public String convertToDatabaseColumn(Rate rate) {
        return rate.toString();
    }

    @Override
    public Rate convertToEntityAttribute(String dbData) {
        return Rate.valueOf(dbData);
    }
}