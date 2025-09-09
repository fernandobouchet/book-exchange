package com.fernandobouchet.book_exchange.service;

import com.fernandobouchet.book_exchange.model.Review;
import com.fernandobouchet.book_exchange.model.User;

public interface ReviewService {
    Review addReview(Long bookId, Long userId, String comment, int rating);
}
