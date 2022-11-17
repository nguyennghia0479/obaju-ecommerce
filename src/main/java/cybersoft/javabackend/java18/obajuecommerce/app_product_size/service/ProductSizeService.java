package cybersoft.javabackend.java18.obajuecommerce.app_product_size.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeDTO;

import java.util.List;
import java.util.UUID;

public interface ProductSizeService {
    List<ProductSizeDTO> findAll();

    List<ProductSizeDTO> findByProductId(UUID id);

    ProductSizeDTO save(ProductSizeCreateDTO productSizeCreateDTO);

    void deleteById(UUID id);
}
