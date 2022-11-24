package cybersoft.javabackend.java18.obajuecommerce.app_product.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.*;
import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import org.springframework.data.domain.Sort;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ByteArrayInputStream exportExcel(int page, int size, Sort sort);

    List<ProductDTO> findAllBySubcategoryId(UUID id);
    List<ProductDTO> findAllBySubcategoryName(String name, int page, int size, Sort sort);

    List<ProductIncludeSubcategoryDTO> findAllIncludeSubcategoryDTO(int page, int size, Sort sort);

    ProductIncludeSubcategoryDTO findById(UUID id);

    ProductIncludeImageDTO findByName(String name);

    ProductDTO save(ProductCreateDTO productCreateDTO);

    ProductDTO update(ProductUpdateDTO productUpdateDTO);

    void deleteById(UUID id);

    void deleteImagesByProductId(UUID id);

    Product.Color[] findAllColor();
}
