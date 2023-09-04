package com.example.demo;

import java.security.*;
import java.nio.charset.StandardCharsets;

public class PwHashing{
    public static String hashing(String password, String saltString) {
        SecureRandom random = new SecureRandom();
        byte[] salt = null;
        if (saltString == null){
            salt = new byte[16];
            random.nextBytes(salt);
        } else {
            salt = decodeHexString(saltString);
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but SHA-256 is not a valid message digest algorithm");
        }
        md.update(salt);
        byte[] bytePassword = password.getBytes(StandardCharsets.UTF_8);
        byte[] hashedPassword = md.digest(bytePassword);
        return (bytesToHex(hashedPassword)) + (bytesToHex(salt));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
              "Invalid hexadecimal String supplied.");
        }
        
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    public static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }
    
    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException(
              "Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
    }
}