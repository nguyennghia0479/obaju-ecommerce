package cybersoft.javabackend.java18.obajuecommerce.app_category.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_category.validation.annotation.UniqueCategoryCode;
import cybersoft.javabackend.java18.obajuecommerce.app_category.validation.annotation.UniqueCategoryName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateDTO implements Serializable {
    @Size(min = 5, max = 20, message = "{category.name.size}")
    @NotBlank(message = "{category.name.blank}")
    @UniqueCategoryName
    private String name;

    @Size(min = 5, max = 20, message = "{category.name.size}")
    @NotBlank(message = "{category.name.blank}")
    @UniqueCategoryCode
    private String code;
}
