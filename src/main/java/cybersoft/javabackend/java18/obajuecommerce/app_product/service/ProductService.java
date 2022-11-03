package cybersoft.javabackend.java18.obajuecommerce.app_product.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductIncludeImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDTO> findAll();

    ProductDTO findById(UUID id);

    ProductIncludeImageDTO findByName(String name);

    ProductDTO save(ProductCreateDTO productCreateDTO);

    ProductDTO update(ProductUpdateDTO productUpdateDTO);

    void deleteById(UUID id);

    void deleteImagesById(UUID id);
}
