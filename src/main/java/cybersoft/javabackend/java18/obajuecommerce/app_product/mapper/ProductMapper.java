package cybersoft.javabackend.java18.obajuecommerce.app_product.mapper;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductIncludeImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductIncludeSubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    ProductIncludeSubcategoryDTO productToProductIncludeSubcategoryDTO(Product product);

    ProductIncludeImageDTO productToProductIncludeImageDTO(Product product);

    Product productCreateDTOToProduct(ProductCreateDTO productCreateDTO);
}
