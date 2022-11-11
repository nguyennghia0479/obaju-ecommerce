package cybersoft.javabackend.java18.obajuecommerce.app_product_size.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;

import java.util.List;
import java.util.UUID;

public interface ProductSizeService {
    List<ProductSizeDTO> findAll();

    List<ProductSizeDTO> findBySizeType(ProductSize.SizeType sizeType);

    ProductSize.SizeType[] findAllSizeType();

    ProductSizeDTO save(ProductSizeCreateDTO productSizeCreateDTO);

    void deleteById(UUID id);
}
