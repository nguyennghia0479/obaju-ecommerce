package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

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
    private String code;
    private String description;
}
