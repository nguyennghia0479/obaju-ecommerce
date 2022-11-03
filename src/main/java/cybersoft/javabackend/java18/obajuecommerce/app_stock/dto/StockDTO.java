package cybersoft.javabackend.java18.obajuecommerce.app_stock.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeDTO;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO implements Serializable {
    private UUID id;
    private ProductDTO product;
    private ProductSizeDTO productSize;
    private int quantity;
}
