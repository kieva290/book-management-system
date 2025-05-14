package com.book.management.system.book;

import com.book.management.system.book.dao.BookSearchDao;
import com.book.management.system.common.ISBNGenerator;
import com.book.management.system.common.IdempotencyKey;
import com.book.management.system.common.IdempotencyKeyRepository;
import com.book.management.system.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final IdempotencyKeyRepository idempotencyKeyRepository;
    private final BookMapper bookMapper;
    private final ISBNGenerator isbnGenerator;
    private final BookSearchDao bookSearchDao;

    public String save(String idempotencyKey, @Valid BookRequest request) {
        IdempotencyKey savedKey = idempotencyKeyRepository.findByPotencyKey(idempotencyKey).orElse(null);

        if (savedKey != null) {
            if (savedKey.getExpiryDate().isBefore(LocalDateTime.now())) {
                idempotencyKeyRepository.delete(savedKey);
            } else {
                return "Book already saved " + savedKey.getResponse();
            }
        }

        Book book = bookMapper.toBook(request);
        book.setIsbn(isbnGenerator.generateISBN13());
        bookRepository.save(book);

        IdempotencyKey newKey = new IdempotencyKey();
        newKey.setPotencyKey(idempotencyKey);
        newKey.setResponse("Book saved with ID: " + book.getId());
        newKey.setExpiryDate(LocalDateTime.now());
        idempotencyKeyRepository.save(newKey);

        return "Book saved with ID: " + book.getId();

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
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found, could not be delete"));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    public PageResponse<BookResponse> searchAllBooksByTitleOrAuthor(String searchPhrase) {
        List<Book> books = bookSearchDao.findAllBySimpleQuery(searchPhrase, searchPhrase);

        Page<Book> booksPage = new PageImpl<>(books);
        List<BookResponse> booksResponse = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return new PageResponse<>(
                booksResponse,
                booksPage.getNumber(),
                booksPage.getSize(),
                booksPage.getTotalElements(),
                booksPage.getTotalPages(),
                booksPage.isFirst(),
                booksPage.isLast()
        );
    }

}
