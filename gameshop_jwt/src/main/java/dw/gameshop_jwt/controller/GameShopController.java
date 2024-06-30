package dw.gameshop_jwt.controller;

import dw.gameshop_jwt.dto.BaseResponse;
import dw.gameshop_jwt.enumstatus.ResultCode;
import dw.gameshop_jwt.model.Game;
import dw.gameshop_jwt.model.User;
import dw.gameshop_jwt.service.GameShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameShopController {
    GameShopService gameShopService;

    public GameShopController(GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    @GetMapping("/products")
    public ResponseEntity<BaseResponse<List<Game>>> getAllGames() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.getAllGames(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<BaseResponse<Game>> getGameById(@PathVariable long id) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.getGameById(id),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<BaseResponse<Game>> updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.updateGameById(id, game),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @PostMapping("/products/user")
    public ResponseEntity<BaseResponse<User>> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.saveUser(user),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/maxprice")
    public ResponseEntity<BaseResponse<Game>> getGameWithMaxPrice() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.getGameWithMaxPrice(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/maxpricetop3")
    public ResponseEntity<BaseResponse<List<Game>>> getGameWithMaxPriceTop3() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.getGameWithMaxPriceTop3(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }
}





