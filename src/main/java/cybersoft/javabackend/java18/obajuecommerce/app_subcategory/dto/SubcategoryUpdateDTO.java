package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryUpdateDTO implements Serializable {
    private UUID id;
    private String description;
}
