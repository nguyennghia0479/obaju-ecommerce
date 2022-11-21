package cybersoft.javabackend.java18.obajuecommerce.app_order.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.service.CartService;
import cybersoft.javabackend.java18.obajuecommerce.app_order.dto.OrderDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_order.service.OrderService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    @GetMapping("/orders/user/{username}")
    public ResponseEntity<ResponseDTO> findAllOrderByUsername(@PathVariable("username") String username) {
        return ResponseUtils.get(orderService.findAllByUsername(username), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.FIND_BY_ID, type = Operation.Type.FETCH)
    @GetMapping("/orders/{id}/orderItem")
    public ResponseEntity<ResponseDTO> findAllOrderItemByOrderId(@PathVariable("id") UUID id) {
        return ResponseUtils.get(orderService.findAllByOrderId(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    @GetMapping("/orders")
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(orderService.findAll(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.PLACE_ORDER, type = Operation.Type.SAVE_OR_UPDATE)
    @PostMapping("/orders/place-order")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody @Valid OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO, cartService.findAll());
        cartService.removeAll();
        return ResponseUtils.get("", HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.UPDATE, type = Operation.Type.SAVE_OR_UPDATE)
    @PutMapping("/admin/orders/delivery-success/{id}")
    public ResponseEntity<ResponseDTO> updateDeliverySuccess(@PathVariable("id") UUID id) {
        orderService.updateDeliverySuccess(id);
        return ResponseUtils.get("Delivery Successfully", HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.UPDATE, type = Operation.Type.SAVE_OR_UPDATE)
    @PutMapping("/admin/orders/cancel/{id}")
    public ResponseEntity<ResponseDTO> updateCancelOrder(@PathVariable("id") UUID id) {
        orderService.updateCancelOrder(id);
        return ResponseUtils.get("Cancel Orders Successfully", HttpStatus.OK);
    }
}
