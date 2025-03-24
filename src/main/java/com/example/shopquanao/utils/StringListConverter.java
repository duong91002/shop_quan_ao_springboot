package com.example.shopquanao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> images) {
        if (images == null || images.isEmpty()) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(images);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi khi chuyển danh sách thành JSON", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String imagesJSON) {
        if (imagesJSON == null || imagesJSON.trim().isEmpty()) {
            return new ArrayList<>(); 
        }
        try {
            return objectMapper.readValue(imagesJSON, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi đọc JSON từ database", e);
        }
    }
}
