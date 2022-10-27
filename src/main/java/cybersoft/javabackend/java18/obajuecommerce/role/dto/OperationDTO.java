package cybersoft.javabackend.java18.obajuecommerce.role.dto;

import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.role.validation.annotation.UniqueOperationName;
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
public class OperationDTO implements Serializable {
    private UUID id;

    @Size(min = 5, max = 20, message = "{operation.name.size}")
    @NotBlank(message = "{operation.name.blank}")
    @UniqueOperationName
    private String name;

    private String description;

    private Operation.Type type;
}
