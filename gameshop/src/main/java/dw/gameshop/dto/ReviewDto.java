package dw.gameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReviewDto {
    private long gameId;
    private String gameName;
    private String userId;
    private int reviewPoint;
    private String reviewText;
}
