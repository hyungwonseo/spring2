package dw.gameshop.dto;

import dw.gameshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String userId;
    private String password;
    private String userName;
    private String userEmail;

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                null,
                user.getUsername2(),
                user.getEmail()
        );
    }
}
