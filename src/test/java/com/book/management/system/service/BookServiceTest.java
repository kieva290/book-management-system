package com.book.management.system.service;

import com.book.management.system.book.*;
import com.book.management.system.common.ISBNGenerator;
import com.book.management.system.common.PageResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private ISBNGenerator isbnGenerator;

    @Test
    public void should_successfully_create_book() {
        // GIVEN
        BookRequest bookRequest = new BookRequest(null, "test title", "test author");

        Book book = Book.builder()
                .id(1)
                .author("test author")
                .title("test title")
                .isbn("9780306406157")
                .build();

        // Mock the calls
        when(isbnGenerator.generateISBN13()).thenReturn("9780306406157");
        when(bookMapper.toBook(bookRequest)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Integer bookId = bookService.save(bookRequest);

        assertNotNull(bookId);
        verify(isbnGenerator, times (1)).generateISBN13();
        verify(bookMapper, times (1)).toBook(bookRequest);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void should_return_all_books() {
        // GIVEN
        Page<Book> books = Mockito.mock(Page.class);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("createdDate").descending());

        // Mock the calls
        when(bookRepository.findAllBooks(pageable)).thenReturn(books);

        PageResponse<BookResponse> pageResponse = bookService.findAllBooks(0, 5);

        Assertions.assertThat(pageResponse).isNotNull();

    }

    @Test
    public void find_book_by_id() {
        // GIVEN
        int bookId = 1;
        Book book = Book.builder()
                .id(1)
                .author("test author")
                .title("test title")
                .isbn("9780306406157")
                .build();

        BookResponse bookResponse = BookResponse.builder()
                .id(1)
                .author("test author")
                .title("test title")
                .isbn("9780306406157")
                .build();

        // Mock the calls
        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));
        when(bookMapper.toBookResponse(book)).thenReturn(bookResponse);

        BookResponse bookResponse2 = bookService.findById(bookId);

        Assertions.assertThat(bookResponse2).isNotNull();

    }

    @Test
    public void update_book_successfully() {
        // GIVEN
        int bookId = 1;
        Book book = Book.builder()
                .id(1)
                .author("test author")
                .title("test title")
                .isbn("9780306406157")
                .build();

        BookResponse bookResponse = BookResponse.builder()
                .id(1)
                .author("test author")
                .title("test title")
                .isbn("9780306406157")
                .build();;

        BookRequest bookRequest = new BookRequest(1, "update title", "update author");

        // Mock the calls
        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toBookResponse(book)).thenReturn(bookResponse);

        BookResponse bookUpdateResponse = bookService.updateBook(bookId, bookRequest);

        Assertions.assertThat(bookUpdateResponse).isNotNull();

    }

    @Test
    public void delete_book_successfully() {
        int bookId = 1;
        Book book = Book.builder()
                .id(1)
                .author("test author")
                .title("test title")
                .isbn("9780306406157")
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));
        doNothing().when(bookRepository).delete(book);

        assertAll(() -> bookService.deleteBook(bookId));

    }

}
