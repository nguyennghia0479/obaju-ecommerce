package cybersoft.javabackend.java18.obajuecommerce.app_order.service;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.dto.CartDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.dto.OrderDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.dto.OrderItemDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void placeOrder(OrderDTO orderDTO, List<CartDTO> carts);

    Order.Payment[] findAllPayment();

    List<OrderDTO> findAllByUsername(String username);

    List<OrderItemDTO> findAllByOrderId(UUID id);

    List<OrderDTO> findAll();

    void updateDeliverySuccess(UUID id);

    void updateCancelOrder(UUID id);
}
