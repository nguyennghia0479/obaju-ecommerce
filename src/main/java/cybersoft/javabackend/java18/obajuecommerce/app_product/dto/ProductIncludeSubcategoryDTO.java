package cybersoft.javabackend.java18.obajuecommerce.app_product.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductIncludeSubcategoryDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String avatarURL;
    private Double price;
    private Product.Color color;
    private SubcategoryDTO subcategory;
}
