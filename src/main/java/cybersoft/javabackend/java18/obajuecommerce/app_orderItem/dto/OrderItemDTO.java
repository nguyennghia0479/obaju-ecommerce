package cybersoft.javabackend.java18.obajuecommerce.app_orderItem.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO implements Serializable {
    private UUID id;
    private int quantity;
    private double price;
    private StockDTO stock;
}
