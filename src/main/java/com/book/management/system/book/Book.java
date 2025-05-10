package com.book.management.system.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
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
    @Column(nullable = false)
    @Size(min = 1, max = 100,  message = "Title Cannot be Exceed 100 characters")
    private String title;
    @Column(nullable = false)
    @Size(min = 1, max = 50, message = "Author Cannot be Exceed 50 characters")
    private String author;
    @Column(nullable = false)
    @Size(min = 13, max = 13, message = "ISBN Must be 13 characters long")
    private String isbn;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

}
