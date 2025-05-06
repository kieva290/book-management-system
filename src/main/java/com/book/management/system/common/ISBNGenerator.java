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
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Integer.parseInt(String.valueOf(isbn12.charAt(i)));
            sum += (i % 2 == 0 ) ? digit : digit * 3;
        }
        int checkDigit = 10 - (sum % 10);
        return (checkDigit == 10) ? 0 : checkDigit;
    }

}
