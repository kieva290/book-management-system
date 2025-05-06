package com.book.management.system.book;

import jakarta.validation.constraints.*;

public record BookRequest(
        Integer id,
        @NotNull(message = "Title Cannot be Null")
        @NotEmpty(message = "Title Cannot be Empty")
        @Size(min = 1, max = 100,  message = "Title Cannot be Exceed 100 characters")
        String title,
        @NotNull(message = "Author Cannot be Null")
        @NotEmpty(message = "Author Cannot be Empty")
        @Size(min = 1, max = 50, message = "Author Cannot be Exceed 50 characters")
        String author,
        @NotNull(message = "ISBN cannot be Null")
        @NotEmpty(message = "ISBN cannot be Empty")
        @Size(min = 13, max = 13, message = "ISBN Must be 13 characters long")
        String isbn

) {
}
