package com.ezgroceries.shoppinglist.helper;

import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> set) {
        if (!CollectionUtils.isEmpty(set)) {
            return "," + String.join(",", set) + ",";
        }
        return null;
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined){
        if (joined != null) {
            String values = joined.substring(1, joined.length() - 1);
            return new HashSet<>(Arrays.asList(values.split(",")));
        }
        return new HashSet<>();
    }
}
