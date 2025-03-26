package com.example.shopquanao.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.stream.Collectors;

public class VNPayUtils {
    
    public static String createQueryUrl(Map<String, String> params, String secretKey) {
        String queryString = params.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
            .collect(Collectors.joining("&"));

        String hashData = hashSHA256(queryString + secretKey);
        return queryString + "&vnp_SecureHash=" + hashData;
    }

    public static boolean validateResponse(Map<String, String> params, String secretKey) {
        String receivedHash = params.remove("vnp_SecureHash");
        String queryString = params.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .collect(Collectors.joining("&"));

        String expectedHash = hashSHA256(queryString + secretKey);
        return expectedHash.equals(receivedHash);
    }

    private static String hashSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}

