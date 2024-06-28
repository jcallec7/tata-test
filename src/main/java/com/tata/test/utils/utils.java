package com.tata.test.utils;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
public class utils {

    public static Map<String, Object> getNonNullFields(Object obj) throws IllegalAccessException {
        Map<String, Object> nonNullFields = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value != null) {
                nonNullFields.put(field.getName(), value);
            }
        }

        return nonNullFields;
    }

}
