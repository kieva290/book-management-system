//package com.book.management.system.controllers;
//
//import com.book.management.system.book.*;
//import org.hamcrest.CoreMatchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@WebMvcTest(BookController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
//public class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Autowired
//    private BookMapper bookMapper;
//    private Book book;
//    private BookRequest bookRequest;
//    private BookResponse bookResponse;
//
//    @BeforeEach
//    public void init() {
//        book = Book.builder().id(1).author("test author").title("test title").isbn("9780306406157").build();
//        bookRequest = new BookRequest(null, "test title", "test author");
//        bookResponse = BookResponse.builder().id(1).author("test author").title("test title").isbn("9780306406157").build();
//    }
//
//    @Test
//    public void BookController_CreateBook() throws Exception {
//        given(bookService.save(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
//
//        ResultActions response = mockMvc.perform(post("/api/v1/books")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(book.getId())));
//    }
//
//}
