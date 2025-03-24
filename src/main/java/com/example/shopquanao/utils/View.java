/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

/**
 *
 * @author haidu
 */
public class View {

    public View() {
    }

    public Map<String, Object> responseList(Page<?> entities) {
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", entities.getNumber());
        pagination.put("totalPages", entities.getTotalPages());
        pagination.put("totalEntries", entities.getNumberOfElements());
        
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "success");
        responseData.put("data", entities.getContent());
        responseData.put("pagination", pagination);
        return responseData;
    }
    
    public Map<String, Object> responseListUnlimited(List<?> data) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "success");
        responseData.put("data", data);
        return responseData;
    }
    
    public Map<String, Object> responseOptional(Optional<?> data) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "success");
        responseData.put("data", data);
        return responseData;
    }
}
