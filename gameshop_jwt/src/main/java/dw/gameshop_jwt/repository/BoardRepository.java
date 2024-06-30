package dw.gameshop_jwt.repository;

import dw.gameshop_jwt.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
