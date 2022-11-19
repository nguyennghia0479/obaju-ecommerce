package cybersoft.javabackend.java18.obajuecommerce.app_cart.service;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.dto.CartDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {
    void addItemToCart(UUID productId, UUID sizeId, Integer quantity);

    void updateCart(UUID stockId, Integer quantity);

    void removeItemFormCart(UUID stockId);

    void removeAll();

    List<CartDTO> findAll();

    Double getTotalPrice();
}
