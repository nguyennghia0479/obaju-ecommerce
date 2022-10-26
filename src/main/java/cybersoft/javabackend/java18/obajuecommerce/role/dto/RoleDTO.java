package cybersoft.javabackend.java18.obajuecommerce.role.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
}
