package cybersoft.javabackend.java18.obajuecommerce.app_product_size.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.mapper.ProductSizeMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.repository.ProductSizeRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.DeleteException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductSizeServiceImpl implements ProductSizeService {
    private final ProductSizeRepository productSizeRepository;

    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    public List<ProductSizeDTO> findAll() {
        Sort sort = Sort.by("lastModifiedAt").descending();
        return productSizeRepository.findAll(sort)
                .stream()
                .map(ProductSizeMapper.INSTANCE::productSizeToProductSizeDTO)
                .toList();
    }

    @Override
    public List<ProductSizeDTO> findByProductId(UUID id) {
        return productSizeRepository.findByProductId(id)
                .stream()
                .map(ProductSizeMapper.INSTANCE::productSizeToProductSizeDTO)
                .toList();
    }

    @Override
    public ProductSizeDTO save(ProductSizeCreateDTO productSizeCreateDTO) {
        ProductSize productSizeCreate = ProductSizeMapper.INSTANCE.productSizeCreateDTOToProductSize(productSizeCreateDTO);
        productSizeRepository.save(productSizeCreate);
        return ProductSizeMapper.INSTANCE.productSizeToProductSizeDTO(productSizeCreate);
    }

    @Override
    public void deleteById(UUID id) {
        ProductSize productSize = productSizeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.PRODUCT_SIZE_ID_NOT_FOUND));
        if(!productSize.getStocks().isEmpty())
            throw new DeleteException(DeleteMessageUtils.DELETE_PRODUCT_SIZE_FAILED);
        productSizeRepository.delete(productSize);
    }
}
