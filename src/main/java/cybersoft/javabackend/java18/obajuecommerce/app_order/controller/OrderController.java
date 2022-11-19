package cybersoft.javabackend.java18.obajuecommerce.app_order.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.service.CartService;
import cybersoft.javabackend.java18.obajuecommerce.app_order.dto.OrderDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.service.OrderService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    private final CartService cartService;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping("/orders/select-payment")
    public ResponseEntity<ResponseDTO> getSelectPayment() {
        return ResponseUtils.get(orderService.findAllPayment(), HttpStatus.OK);
    }

    @PostMapping("/orders/place-order")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody @Valid OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO, cartService.findAll());
        cartService.removeAll();
        return ResponseUtils.get("", HttpStatus.CREATED);
    }
}
