package com.book.management.system.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String author;
    private String isbn;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

}
