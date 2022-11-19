package cybersoft.javabackend.java18.obajuecommerce.app_order.service;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.dto.CartDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.dto.OrderDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import cybersoft.javabackend.java18.obajuecommerce.app_order.repository.OrderRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.model.OrderItem;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.repository.OrderItemRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.repository.StockRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.UserNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.UserNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                            UserRepository userRepository, StockRepository stockRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public void placeOrder(OrderDTO orderDTO, List<CartDTO> carts) {
        User user = userRepository.findByUsername(orderDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundMessageUtils.USERNAME_NOT_FOUND));
        Order newOrder = new Order();
        newOrder.setTotalPrice(orderDTO.getTotalPrice());
        newOrder.setAddress(orderDTO.getAddress());
        newOrder.setPayment(orderDTO.getPayment());
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        for(CartDTO cart : carts) {
            Stock stock = stockRepository.findById(cart.getStock().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.STOCK_ID_NOT_FOUND));
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(cart.getStock().getProduct().getPrice());
            orderItem.setOrder(newOrder);
            orderItem.setStock(stock);
            orderItemRepository.save(orderItem);
            stock.setQuantity(stock.getQuantity() - cart.getQuantity());
        }
    }

    @Override
    public Order.Payment[] findAllPayment() {
        return Order.Payment.values();
    }

    private void saveOrder(OrderDTO orderDTO) {

    }
}
