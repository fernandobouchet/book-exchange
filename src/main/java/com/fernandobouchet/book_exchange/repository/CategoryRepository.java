package com.fernandobouchet.book_exchange.repository;

import com.fernandobouchet.book_exchange.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
