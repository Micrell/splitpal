package com.example.nomoola.database.converter;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static LocalDate convertToDateFromString(String value){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(value, format);
    }

    @TypeConverter
    public static String convertToStringFromDate(LocalDate value){
        return value.format(DateTimeFormatter.ofPattern("d-MM-yyyy"));
    }
}
