package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.annotation.UniqueSubcategoryCode;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.annotation.UniqueSubcategoryName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    @Size(min = 2, max = 10, message = "{subcategory.code.size}")
    @NotBlank(message = "{subcategory.code.blank}")
    @UniqueSubcategoryCode
    private String code;

    private String description;

    private Subcategory.Category category;
}
