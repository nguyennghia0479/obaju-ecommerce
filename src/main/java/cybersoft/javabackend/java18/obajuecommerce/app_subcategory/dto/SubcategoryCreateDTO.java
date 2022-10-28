package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.annotation.UniqueSubcategoryName;
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
public class SubcategoryCreateDTO implements Serializable {
    @Size(min = 5, max = 20, message = "{subcategory.name.size}")
    @NotBlank(message = "{subcategory.name.blank}")
    @UniqueSubcategoryName
    private String name;

    private String description;

    private UUID categoryId;
}
