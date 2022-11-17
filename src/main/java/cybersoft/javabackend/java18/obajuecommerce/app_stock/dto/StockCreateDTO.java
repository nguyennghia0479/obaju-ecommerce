package cybersoft.javabackend.java18.obajuecommerce.app_stock.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCreateDTO implements Serializable {
    @NotNull(message = "{stock.productId.null}")
    private UUID productId;

    @NotNull(message = "{stock.productSizeId.null}")
    private UUID productSizeId;

    @Min(value = 0, message = "{stock.quantity.min}")
    @NotNull(message = "{stock.quantity.null}")
    private Integer quantity;
}
