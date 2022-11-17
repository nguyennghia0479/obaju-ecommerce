package cybersoft.javabackend.java18.obajuecommerce.app_product.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.*;
import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDTO> findAllBySubcategoryId(UUID id);
    List<ProductDTO> findAllBySubcategoryName(String name);

    List<ProductIncludeSubcategoryDTO> findAllIncludeSubcategoryDTO();

    ProductIncludeSubcategoryDTO findById(UUID id);

    ProductIncludeImageDTO findByName(String name);

    ProductDTO save(ProductCreateDTO productCreateDTO);

    ProductDTO update(ProductUpdateDTO productUpdateDTO);

    void deleteById(UUID id);

    void deleteImagesByProductId(UUID id);

    Product.Color[] findAllColor();
}
