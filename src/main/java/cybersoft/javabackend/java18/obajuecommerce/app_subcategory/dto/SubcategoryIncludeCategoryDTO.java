package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryDTO;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryIncludeCategoryDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private CategoryDTO category;
}
