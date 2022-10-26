package cybersoft.javabackend.java18.obajuecommerce.role.dto;

import cybersoft.javabackend.java18.obajuecommerce.role.validation.annotation.UniqueRoleCode;
import cybersoft.javabackend.java18.obajuecommerce.role.validation.annotation.UniqueRoleName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleCreateDTO implements Serializable {
    @Size(min = 5, max = 20, message = "{role.name.size}")
    @NotBlank(message = "{role.name.blank}")
    @UniqueRoleName
    private String name;

    @Size(min = 3, max = 20, message = "{role.code.size}")
    @NotBlank(message = "{role.code.blank}")
    @UniqueRoleCode
    private String code;

    private String description;
}
