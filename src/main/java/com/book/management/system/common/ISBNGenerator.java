package com.book.management.system.common;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ISBNGenerator {

    public String generateISBN13() {
        String isbn12 = generateISBN12();
        int checkDigit = calculateCheckDigit(isbn12);
        return isbn12 + checkDigit;
    }

    private String generateISBN12() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }

    private int calculateCheckDigit(String isbn12) {

        int checksum = 0; // holds the checksum value

        for (int i = 0; i < isbn12.length() ; i++) {
            checksum += i % 2 == 0 ? 3 * Integer.parseInt(isbn12.charAt(i) + "") : Integer.parseInt(isbn12.charAt(i) + "");
        }

        checksum = 10 - checksum % 10;

        return checksum;

    }

}
