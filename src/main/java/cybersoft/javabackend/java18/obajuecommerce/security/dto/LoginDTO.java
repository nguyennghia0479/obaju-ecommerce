package cybersoft.javabackend.java18.obajuecommerce.security.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO implements Serializable {
    private String username;
    private String password;
}
