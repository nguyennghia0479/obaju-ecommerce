package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryDTO implements Serializable {
    private UUID id;
    private String name;
    private String nameURL;
    private String code;
    private String description;
    private Subcategory.Category category;
}
