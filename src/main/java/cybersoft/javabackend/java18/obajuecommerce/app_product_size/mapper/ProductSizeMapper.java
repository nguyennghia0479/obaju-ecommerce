package cybersoft.javabackend.java18.obajuecommerce.app_product_size.mapper;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSizeMapper {
    ProductSizeMapper INSTANCE = Mappers.getMapper(ProductSizeMapper.class);

    ProductSizeDTO productSizeToProductSizeDTO(ProductSize productSize);

    ProductSize productSizeCreateDTOToProductSize(ProductSizeCreateDTO productSizeCreateDTO);
}
