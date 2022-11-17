package cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSizeDTO implements Serializable {
    private UUID id;
    private String size;
}
