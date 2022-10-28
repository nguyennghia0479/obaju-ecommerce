package cybersoft.javabackend.java18.obajuecommerce.user.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroupIncludeUserDTO implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private Set<UserDTO> users;
}
