package com.fernandobouchet.book_exchange.service.impl;

import com.fernandobouchet.book_exchange.model.Book;
import com.fernandobouchet.book_exchange.model.Review;
import com.fernandobouchet.book_exchange.model.User;
import com.fernandobouchet.book_exchange.repository.BookRepository;
import com.fernandobouchet.book_exchange.repository.ReviewRepository;
import com.fernandobouchet.book_exchange.repository.UserRepository;
import com.fernandobouchet.book_exchange.service.ReviewService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Override
    public Review addReview(Long bookId, Long userId, String comment, int rating) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exists in the db"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book does not exists with ID" + bookId));

        if(reviewRepository.existsByBookIdAndUserId(bookId,userId)) {
            throw new EntityExistsException("User already reviewed this book");
        }

        Review review = Review.builder()
                .book(book)
                .user(user)
                .comment(comment)
                .rating(rating)
                .build();

        reviewRepository.save(review);

        recalculateBookRating(book);

        return review;
    }


    private void recalculateBookRating(Book book) {
        Double average = reviewRepository.findByBookId(book.getId())
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        book.setRating(average);
        bookRepository.save(book);
    }
}
