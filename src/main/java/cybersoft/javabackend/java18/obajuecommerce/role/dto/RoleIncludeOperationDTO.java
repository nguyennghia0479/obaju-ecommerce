package cybersoft.javabackend.java18.obajuecommerce.role.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleIncludeOperationDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Set<OperationDTO> operations;
}
