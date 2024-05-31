package dw.gameshop.dto;

import dw.gameshop.model.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionDto {
    private String userId;
    private Collection<? extends GrantedAuthority> authority;
}
