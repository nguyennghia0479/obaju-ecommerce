package cybersoft.javabackend.java18.obajuecommerce.app_category.dto;

import lombok.*;

import java.io.Serializable;
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
}
