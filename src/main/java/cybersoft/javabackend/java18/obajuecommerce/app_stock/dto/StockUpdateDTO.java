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
public class StockUpdateDTO implements Serializable {
    private UUID id;

    @Min(value = 0, message = "{stock.quantity.min}")
    @NotNull(message = "{stock.quantity.null}")
    private Integer quantity;
}
