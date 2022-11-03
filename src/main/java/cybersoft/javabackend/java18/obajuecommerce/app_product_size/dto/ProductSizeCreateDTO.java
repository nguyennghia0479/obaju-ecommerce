package cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.validation.annotation.UniqueProductSize;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSizeCreateDTO implements Serializable {
    @Size(min = 1, max = 10, message = "{productSize.size.size}")
    @NotBlank(message = "{productSize.size.blank}")
    @UniqueProductSize
    private String size;

    private ProductSize.SizeType sizeType;
}
