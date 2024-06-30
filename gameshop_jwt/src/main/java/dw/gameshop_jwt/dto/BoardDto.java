package dw.gameshop_jwt.dto;

import dw.gameshop_jwt.model.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private UserDto author;
    private LocalDateTime modifiedDate;

    public static BoardDto toBoardDto(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                UserDto.toUserDto(board.getAuthor()),
                board.getModifiedDate()
        );
    }
}
