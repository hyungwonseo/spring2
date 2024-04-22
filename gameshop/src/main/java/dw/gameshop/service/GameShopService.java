package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameShopRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameShopService {
    GameShopRepository gameShopRepository;
    UserRepository userRepository;

    public GameShopService(GameShopRepository gameShopRepository, UserRepository userRepository) {
        this.gameShopRepository = gameShopRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return gameShopRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> gameOptional = gameShopRepository.findById(id);
        if(gameOptional.isPresent()) {
            return gameOptional.get();
        }else {
            throw new ResourceNotFoundException("Game", "ID", id);
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
            throw new ResourceNotFoundException("Game", "ID", id);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //제일 비싼 게임의 정보
    public Game getGameWithMaxPrice() {
        List<Game> games = gameShopRepository.findAll();
        // 람다식이 아닌 일반 자바코드 사용 예
//        if (games.size() <= 0) {
//            throw new ResourceNotFoundException("Max Price", " ", " ");
//        }
//        Game max = games.get(0);
//        for (int i=0; i< games.size()-1; i++) {
//            if (max.getPrice() < games.get(i+1).getPrice()) {
//                max = games.get(i+1);
//            }
//        }
//        return max;
        // 람다식 사용 예
//        return games.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice)
//                .reversed())
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Max Price", " ", " "));
        // JPQL 사용 예
        return gameShopRepository.getGameWithMaxPrice();
    }

    //제일 비싼 게임 Top 3
    public List<Game> getGameWithMaxPriceTop3() {
        List<Game> games = gameShopRepository.findAll();
        // 람다식이 아닌 일반 자바코드 사용 예
//        games.sort(Comparator.comparingInt((Game g) -> g.getPrice()).reversed());
//        List<Game> newGames = new ArrayList<>();
//        newGames.add(games.get(0));
//        newGames.add(games.get(1));
//        newGames.add(games.get(2));
//        return newGames;
        // 람다식 사용 예
//        return games.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
//                .limit(3)
//                .collect(Collectors.toList());
        // JPQL 사용 예
        return gameShopRepository.getGameWithMaxPriceTop3()
                .stream().limit(3).collect(Collectors.toList());
    }
}












