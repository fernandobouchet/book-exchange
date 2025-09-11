package com.fernandobouchet.book_exchange.controller;

import com.fernandobouchet.book_exchange.model.Review;
import com.fernandobouchet.book_exchange.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(
            @RequestParam Long bookId,
            @RequestParam Long userId,
            @RequestParam String comment,
            @RequestParam int rating
    ) {
        Review review = reviewService.addReview(bookId, userId, comment, rating);
        return ResponseEntity.ok(review);
    }
}
