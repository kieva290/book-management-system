package com.book.management.system.book;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public Integer save(@Valid BookRequest request) {
        Book book = bookMapper.toBook(request);
        return bookRepository.save(book).getId();
    }

}
