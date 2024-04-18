package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameShopRepository extends JpaRepository<Game, Long> {
}
