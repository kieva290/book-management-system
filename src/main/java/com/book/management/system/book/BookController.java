package com.book.management.system.book;

import com.book.management.system.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAllBooks(page, size));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(service.findById(bookId));
    }

    @PutMapping("/{book-id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("book-id") Integer bookId, @Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(service.updateBook(bookId, request));
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("book-id") Integer bookId) {
        return service.deleteBook(bookId);
    }

}
