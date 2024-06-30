package dw.gameshop_jwt.dto;

import dw.gameshop_jwt.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank(message = "User ID is mandatory")
    private String userId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    @NotBlank(message = "User name is mandatory")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
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
