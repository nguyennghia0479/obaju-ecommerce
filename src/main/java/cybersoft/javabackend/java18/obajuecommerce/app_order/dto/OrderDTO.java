package cybersoft.javabackend.java18.obajuecommerce.app_order.dto;

import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO implements Serializable {
    private UUID id;
    private String code;
    private Double totalPrice;
    private String address;
    private Order.Payment payment;
    private String username;
    private Order.StatusOrder statusOrder;
    private String orderDate;
}
