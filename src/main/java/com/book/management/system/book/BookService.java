package com.book.management.system.book;

import com.book.management.system.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public PageResponse<BookResponse> findAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAllBooks(pageable);
        List<BookResponse> booksResponse = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return new PageResponse<>(
                booksResponse,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public BookResponse findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + bookId));
    }

    public BookResponse updateBook(Integer bookId, @Valid BookRequest request) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setAuthor(request.author());
                    book.setTitle(request.title());
                    return bookMapper.toBookResponse(bookRepository.save(book));
                })
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + bookId));

    }

    public ResponseEntity<Void> deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }

}
