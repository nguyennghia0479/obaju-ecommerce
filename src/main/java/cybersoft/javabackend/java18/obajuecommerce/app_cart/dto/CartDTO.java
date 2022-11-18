package cybersoft.javabackend.java18.obajuecommerce.app_cart.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO implements Serializable {
    private StockDTO stock;
    private Integer quantity;
}
