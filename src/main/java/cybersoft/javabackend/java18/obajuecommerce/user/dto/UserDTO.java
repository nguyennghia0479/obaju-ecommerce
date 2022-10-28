package cybersoft.javabackend.java18.obajuecommerce.user.dto;

import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    private UUID id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNum;
    private User.Status status;
}
