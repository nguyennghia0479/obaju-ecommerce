package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryIncludeProductDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Subcategory.Category category;
    private Set<ProductDTO> products;
}
