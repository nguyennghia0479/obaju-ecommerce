package cybersoft.javabackend.java18.obajuecommerce.app_stock.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import cybersoft.javabackend.java18.obajuecommerce.app_product.repository.ProductRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.repository.ProductSizeRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.mapper.StockMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.repository.StockRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository, ProductSizeRepository productSizeRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    public List<StockDTO> findAll() {
        return stockRepository.findAll()
                .stream()
                .map(StockMapper.INSTANCE::stockToStockDTO)
                .toList();
    }

    @Override
    public StockDTO save(StockCreateDTO stockCreateDTO) {
        Optional<Stock> stockUpdate = stockRepository
                .findByProductIdAndProductSizeId(stockCreateDTO.getProductId(), stockCreateDTO.getProductSizeId());
        if(stockUpdate.isPresent())
            return update(stockUpdate.get(), stockCreateDTO.getQuantity());

        Product product = productRepository.findById(stockCreateDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.PRODUCT_ID_NOT_FOUND));
        ProductSize productSize = productSizeRepository.findById(stockCreateDTO.getProductSizeId())
                .orElseThrow(() -> new ResourceNotFoundException((ResourceNotFoundMessageUtils.PRODUCT_SIZE_ID_NOT_FOUND)));
        Stock stockCreate = new Stock(product, productSize, stockCreateDTO.getQuantity());
        stockRepository.save(stockCreate);
        return StockMapper.INSTANCE.stockToStockDTO(stockCreate);
    }

    @Override
    public void deleteById(UUID id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        stockRepository.delete(stock);
    }

    private StockDTO update(Stock stockUpdate, int quantity) {
        stockUpdate.setQuantity(quantity);
        return StockMapper.INSTANCE.stockToStockDTO(stockUpdate);
    }
}

