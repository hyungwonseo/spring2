package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.service.GameShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameShopController {
    GameShopService gameShopService;

    public GameShopController(GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    @GetMapping("/products")
    public List<Game> getAllGames() {
        return gameShopService.getAllGames();
    }

    @GetMapping("/products/{id}")
    public Game getGameById(@PathVariable long id) {
        return gameShopService.getGameById(id);
    }

    @PutMapping("/products/{id}")
    public Game updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return gameShopService.updateGameById(id, game);
    }
}
