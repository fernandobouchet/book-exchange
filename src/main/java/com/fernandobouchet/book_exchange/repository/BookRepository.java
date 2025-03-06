package com.fernandobouchet.book_exchange.repository;

import com.fernandobouchet.book_exchange.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
