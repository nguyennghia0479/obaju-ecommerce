package cybersoft.javabackend.java18.obajuecommerce.app_cart.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.service.CartService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public ResponseEntity<ResponseDTO> findAllItemInCarts() {
        return ResponseUtils.get(cartService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/carts")
    public ResponseEntity<ResponseDTO> addItemToCart(@RequestParam("productId") UUID productId,
                                                     @RequestParam("sizeId") UUID sizeId,
                                                     @RequestParam("quantity") Integer quantity) {
        cartService.addItemToCart(productId, sizeId, quantity);
        return ResponseUtils.get(cartService.findAll(), HttpStatus.CREATED);
    }

    @PutMapping("/carts")
    public ResponseEntity<ResponseDTO> updateItemInCart(@RequestParam("stockId") UUID stockId,
                                                        @RequestParam("quantity") Integer quantity) {
        cartService.updateCart(stockId, quantity);
        return ResponseUtils.get("Update item successfully", HttpStatus.OK);
    }

    @DeleteMapping("/carts/item")
    public ResponseEntity<ResponseDTO> deleteItemFromCart(@RequestParam("stockId") UUID stockId) {
        cartService.removeItemFormCart(stockId);
        return ResponseUtils.get(cartService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/carts")
    public ResponseEntity<ResponseDTO> deleteCart() {
        cartService.removeAll();
        return ResponseUtils.get(cartService.findAll(), HttpStatus.OK);
    }
}
