package com.book.management.system.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("""
            SELECT book FROM 
            Book book            
            """)
    Page<Book> findAllBooks(Pageable pageable);

    @Query("""
            SELECT book FROM 
            Book book
            where book.author ilike :searchPhrase
            OR book.title ilike :searchPhrase                                  
            """)
    Page<Book> findAllBooksByAuthorOrTitle(@Param("searchPhrase") String searchPhrase, Pageable pageable);

    Page<Book> findAllBooksByTitle(String title, Pageable pageable);

}
