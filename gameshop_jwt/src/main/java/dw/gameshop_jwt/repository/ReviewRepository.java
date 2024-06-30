package dw.gameshop_jwt.repository;

import dw.gameshop_jwt.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
