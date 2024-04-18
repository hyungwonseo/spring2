package dw.gameshop.service;

import dw.gameshop.model.Game;
import dw.gameshop.repository.GameShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class GameShopService {
    GameShopRepository gameShopRepository;

    public GameShopService(GameShopRepository gameShopRepository) {
        this.gameShopRepository = gameShopRepository;
    }

    public List<Game> getAllGames() {
        return gameShopRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> gameOptional = gameShopRepository.findById(id);
        if(gameOptional.isPresent()) {
            return gameOptional.get();
        }else {
            return null;
        }
    }

    public Game updateGameById(long id, Game game) {
        Optional<Game> gameOptional = gameShopRepository.findById(id);
        if(gameOptional.isPresent()) {
            Game temp = gameOptional.get();
            temp.setTitle(game.getTitle());
            temp.setGenre(game.getGenre());
            temp.setPrice(game.getPrice());
            temp.setImage(game.getImage());
            temp.setText(game.getText());
            gameShopRepository.save(temp);
            return temp;
        }else {
            return null;
        }
    }
}
