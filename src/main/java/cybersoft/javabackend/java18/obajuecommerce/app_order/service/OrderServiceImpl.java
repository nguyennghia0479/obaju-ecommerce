package cybersoft.javabackend.java18.obajuecommerce.app_order.service;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.dto.CartDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.dto.OrderDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import cybersoft.javabackend.java18.obajuecommerce.app_order.repository.OrderRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.dto.OrderItemDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.model.OrderItem;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.repository.OrderItemRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.mapper.StockMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.repository.StockRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.UserNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DateTimeUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.UserNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        newOrder.setCode(generateOrderCode());
        newOrder.setTotalPrice(orderDTO.getTotalPrice());
        newOrder.setAddress(orderDTO.getAddress());
        newOrder.setPayment(orderDTO.getPayment());
        newOrder.setStatusOrder(Order.StatusOrder.PREPARED);
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

    @Override
    public List<OrderDTO> findAllByUsername(String username) {
        List<Order> orders = orderRepository.findAllByUsername(username);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            OrderDTO orderDTO = orderToOrderDTO(order);
            orderDTOS.add(orderDTO);
        });
        return orderDTOS;
    }

    @Override
    public List<OrderItemDTO> findAllByOrderId(UUID id) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(id);
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setId(orderItem.getId());
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setPrice(orderItem.getPrice());
            orderItemDTO.setStock(StockMapper.INSTANCE.stockToStockDTO(orderItem.getStock()));
            orderItemDTOS.add(orderItemDTO);
        });
        return orderItemDTOS;
    }

    @Override
    public List<OrderDTO> findAll() {
        Sort sort = Sort.by("lastModifiedAt").descending();
        List<Order> orders = orderRepository.findAll(sort);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            OrderDTO orderDTO = orderToOrderDTO(order);
            orderDTOS.add(orderDTO);
        });
        return orderDTOS;
    }

    @Override
    public void updateDeliverySuccess(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ORDER_ID_NOT_FOUND));
        order.setStatusOrder(Order.StatusOrder.RECEIVED);
    }

    @Override
    public void updateCancelOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ORDER_ID_NOT_FOUND));
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(order.getId());
        orderItems.forEach(orderItem -> {
            Stock stock = stockRepository.findById(orderItem.getStock().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.STOCK_ID_NOT_FOUND));
            stock.setQuantity(stock.getQuantity() + orderItem.getQuantity());
        });
        order.setStatusOrder(Order.StatusOrder.CANCELLED);
    }

    private OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCode(order.getCode());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPayment(order.getPayment());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setUsername(order.getUser().getUsername());
        orderDTO.setStatusOrder(order.getStatusOrder());
        orderDTO.setOrderDate(DateTimeUtils.getDateFormat(order.getCreatedAt()));
        return orderDTO;
    }

    private String generateOrderCode() {
        StringBuilder builder = new StringBuilder();
        int count = orderRepository.countAllOrder();
        return builder
                .append("#")
                .append(count + 1).toString();
    }
}
