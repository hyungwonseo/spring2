package dw.gameshop.service;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.model.Review;
import dw.gameshop.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewAll() {
        return reviewRepository.findAll();
    }

    public List<ReviewDto> getReviewAllByDto() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(int i=0; i< reviews.size(); i++) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReviewPoint(reviews.get(i).getPoint());
            reviewDto.setReviewText(reviews.get(i).getReviewText());
            reviewDto.setUserId(reviews.get(i).getUser().getUserId());
            reviewDto.setGameId(reviews.get(i).getGame().getId());
            reviewDto.setGameName(reviews.get(i).getGame().getTitle());
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }
}
