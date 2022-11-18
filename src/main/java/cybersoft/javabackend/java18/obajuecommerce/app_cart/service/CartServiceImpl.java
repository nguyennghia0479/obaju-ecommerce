package cybersoft.javabackend.java18.obajuecommerce.app_cart.service;

import cybersoft.javabackend.java18.obajuecommerce.app_cart.dto.CartDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.mapper.StockMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.repository.StockRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final StockRepository stockRepository;
    private HashMap<UUID, CartDTO> cartItems;

    public CartServiceImpl(StockRepository stockRepository, HashMap<UUID, CartDTO> cartItems) {
        this.stockRepository = stockRepository;
        this.cartItems = new HashMap<>();
    }

    public HashMap<UUID, CartDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<UUID, CartDTO> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public void addItemToCart(UUID productId, UUID sizeId, Integer quantity) {
        StockDTO stock = stockRepository.findByProductIdAndProductSizeId(productId, sizeId)
                .map(StockMapper.INSTANCE::stockToStockDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.STOCK_ID_NOT_FOUND));
        CartDTO item;
        if(cartItems.containsKey(stock.getId())) {
            item = cartItems.get(stock.getId());
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartDTO();
            item.setQuantity(quantity);
        }
        item.setStock(stock);
        cartItems.put(stock.getId(), item);
    }

    @Override
    public void updateCart(UUID stockId, Integer quantity) {
        StockDTO stock = stockRepository.findById(stockId)
                .map(StockMapper.INSTANCE::stockToStockDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.STOCK_ID_NOT_FOUND));
        CartDTO item = cartItems.get(stock.getId());
        item.setQuantity(quantity);
        item.setStock(stock);
        cartItems.put(stock.getId(), item);
    }

    @Override
    public void removeItemFormCart(UUID stockId) {
        cartItems.remove(stockId);
    }

    @Override
    public void removeAll() {
        cartItems.clear();
    }

    @Override
    public List<CartDTO> findAll() {
        return new ArrayList<>(cartItems.values());
    }
}
