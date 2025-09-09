package com.fernandobouchet.book_exchange.repository;

import com.fernandobouchet.book_exchange.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByBookIdAndUserId(Long bookId, Long userId);

    List<Review> findByBookId(Long bookId);
}
