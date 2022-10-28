package cybersoft.javabackend.java18.obajuecommerce.app_category.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private Set<SubcategoryDTO> subcategories;
}
