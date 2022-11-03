package cybersoft.javabackend.java18.obajuecommerce.app_stock.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCreateDTO implements Serializable {
    private UUID productId;
    private UUID productSizeId;
    private int quantity;
}
