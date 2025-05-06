package com.book.management.system.book;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookMapper {

    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }

}
