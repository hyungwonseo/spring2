package dw.gameshop.dto;

import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDto {
    private long id;
    private Game game;
    private UserDto user;
    private LocalDateTime purchaseTime;

    public static PurchaseDto toPurchaseDto(Purchase purchase) {
        return new PurchaseDto(
                purchase.getId(),
                purchase.getGame(),
                UserDto.toUserDto(purchase.getUser()),
                purchase.getPurchaseTime()
        );
    }
}
