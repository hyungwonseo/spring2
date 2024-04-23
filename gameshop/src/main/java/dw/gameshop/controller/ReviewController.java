package dw.gameshop.controller;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.model.Review;
import dw.gameshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.saveReview(review),
                HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviewAll() {
        return new ResponseEntity<>(reviewService.getReviewAll(),
                HttpStatus.OK);
    }

    @GetMapping("/reviews/dto")
    public ResponseEntity<List<ReviewDto>> getReviewAllByDto() {
        return new ResponseEntity<>(reviewService.getReviewAllByDto(),
                HttpStatus.OK);
    }
}












