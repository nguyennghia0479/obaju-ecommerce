package cybersoft.javabackend.java18.obajuecommerce.app_order.service;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.dto.CartDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.dto.OrderDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderDTO orderDTO, List<CartDTO> carts);

    Order.Payment[] findAllPayment();
}
