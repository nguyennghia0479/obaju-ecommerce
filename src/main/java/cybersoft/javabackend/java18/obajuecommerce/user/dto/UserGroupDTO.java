package cybersoft.javabackend.java18.obajuecommerce.user.dto;

import cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation.UniqueUserGroupName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroupDTO implements Serializable {
    private UUID id;

    @Size(min = 5, max = 20, message = "{userGroup.name.size}")
    @NotBlank(message = "{userGroup.name.blank}")
    @UniqueUserGroupName
    private String name;

    private String description;
}
