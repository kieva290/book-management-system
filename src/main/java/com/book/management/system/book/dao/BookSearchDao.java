package com.book.management.system.book.dao;

import com.book.management.system.book.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookSearchDao {

    private final EntityManager entityManager;

    public List<Book> findAllBySimpleQuery(
            String author,
            String title
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        // select * from book
        Root<Book> root = criteriaQuery.from(Book.class);

        // prepare WHERE clause
        Predicate authorPredicate = criteriaBuilder
                .like(root.get("author"), "%" + author + "%");
        Predicate titlePredicate = criteriaBuilder
                .like(root.get("title"), "%" + title + "%");
        Predicate orPredicate = criteriaBuilder.or(
                authorPredicate,
                titlePredicate);

        criteriaQuery.where(orPredicate);
        TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }

}
