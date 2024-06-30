package dw.gameshop.controller;

import dw.gameshop.dto.BaseResponse;
import dw.gameshop.dto.ReviewDto;
import dw.gameshop.enumstatus.ResultCode;
import dw.gameshop.model.Review;
import dw.gameshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<BaseResponse<Review>> saveReview(@RequestBody Review review) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        reviewService.saveReview(review),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.CREATED);
    }

    @GetMapping("/reviews")
    public ResponseEntity<BaseResponse<List<Review>>> getReviewAll() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        reviewService.getReviewAll(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/reviews/dto")
    public ResponseEntity<BaseResponse<List<ReviewDto>>> getReviewAllByDto() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        reviewService.getReviewAllByDto(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }
}












