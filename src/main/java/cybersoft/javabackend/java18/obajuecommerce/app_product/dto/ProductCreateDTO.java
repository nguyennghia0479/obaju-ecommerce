package cybersoft.javabackend.java18.obajuecommerce.app_product.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import cybersoft.javabackend.java18.obajuecommerce.app_product.validation.annotation.Image;
import cybersoft.javabackend.java18.obajuecommerce.app_product.validation.annotation.UniqueProductName;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateDTO implements Serializable {
    @Size(min = 5, max = 100, message = "{product.name.size}")
    @NotBlank(message = "{product.name.blank}")
    @UniqueProductName
    private String name;

    @Image
    private transient MultipartFile file;

    @NotNull(message = "{product.price.null}")
    private Double price;

    private Product.Color color;

    private UUID subcategoryId;
}
