package cybersoft.javabackend.java18.obajuecommerce.app_product.service;

import cybersoft.javabackend.java18.obajuecommerce.app_image.service.ImageService;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.*;
import cybersoft.javabackend.java18.obajuecommerce.app_product.mapper.ProductMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import cybersoft.javabackend.java18.obajuecommerce.app_product.repository.ProductRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.repository.SubcategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.DeleteException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.DuplicateException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.FileException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.image.FileRestController;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.*;
import cybersoft.javabackend.java18.obajuecommerce.config.FileConfig;
import org.apache.commons.io.FilenameUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ImageService imageService;
    private final Path root;

    public ProductServiceImpl(ProductRepository productRepository, SubcategoryRepository subcategoryRepository,
                              ImageService imageService, FileConfig fileConfig) {
        this.productRepository = productRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.imageService = imageService;
        this.root = Paths.get(fileConfig.getUploadDir());
        try {
            if (!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            throw new FileException(FileExceptionMessageUtils.ERROR_CREATE_ROOT_FOLDER + " " + e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream exportExcel(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        List<ProductIncludeSubcategoryDTO> products = productRepository.findAll(pageable)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductIncludeSubcategoryDTO)
                .toList();
        return ProductToExcel.productToExcel(products);
    }

    @Override
    public List<ProductIncludeSubcategoryDTO> findAllIncludeSubcategoryDTO(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductIncludeSubcategoryDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> findAllBySubcategoryName(String name, int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAllBySubcategoryName(name, pageable)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> findAllBySubcategoryId(UUID id) {
        return productRepository.findAllBySubcategoryId(id)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }

    @Cacheable("Product")
    @Override
    public ProductIncludeSubcategoryDTO findById(UUID id) {
        try {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productRepository.findById(id)
                .map(ProductMapper.INSTANCE::productToProductIncludeSubcategoryDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.PRODUCT_ID_NOT_FOUND));
    }

    @Override
    public ProductIncludeImageDTO findByName(String name) {
        return productRepository.getByName(name)
                .map(ProductMapper.INSTANCE::productToProductIncludeImageDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.PRODUCT_NAME_NOT_FOUND));
    }

    @Override
    public ProductDTO save(ProductCreateDTO productCreateDTO) {
        Subcategory subcategory = subcategoryRepository.findById(productCreateDTO.getSubcategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.SUBCATEGORY_ID_NOT_FOUND));
        Product productCreate = ProductMapper.INSTANCE.productCreateDTOToProduct(productCreateDTO);
        productCreate.setNameURL(ConvertUtils.convertStringToURL(productCreate.getName()));
        productCreate.setCode(generateCode(subcategory.getCode()));
        productCreate.setAvatarURL(uploadAvatar(productCreateDTO.getFile()));
        productCreate.setSubcategory(subcategory);
        productRepository.save(productCreate);
        return ProductMapper.INSTANCE.productToProductDTO(productCreate);
    }

    @Override
    public ProductDTO update(ProductUpdateDTO productUpdateDTO) {
        Subcategory subcategory = subcategoryRepository.findById(productUpdateDTO.getSubcategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.SUBCATEGORY_ID_NOT_FOUND));
        Product productUpdate = productRepository.findById(productUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException((ResourceNotFoundMessageUtils.PRODUCT_ID_NOT_FOUND)));
        checkProductNameIsUnique(productUpdateDTO, productUpdate.getName());
        if (!productUpdate.getSubcategory().getId().equals(subcategory.getId())) {
            productUpdate.setCode(generateCode(subcategory.getCode()));
            productUpdate.setSubcategory(subcategory);
        }
        if (productUpdateDTO.getFile() != null) {
            deleteFileFromSystem(productUpdate.getAvatarURL());
            productUpdate.setAvatarURL(uploadAvatar(productUpdateDTO.getFile()));
        }
        productUpdate.setName(productUpdateDTO.getName());
        productUpdate.setNameURL(ConvertUtils.convertStringToURL(productUpdateDTO.getName()));
        productUpdate.setPrice(productUpdateDTO.getPrice());
        productUpdate.setColor(productUpdateDTO.getColor());
        return ProductMapper.INSTANCE.productToProductDTO(productUpdate);
    }

    @Override
    public void deleteById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.PRODUCT_ID_NOT_FOUND));
        if (!product.getStocks().isEmpty()) {
            throw new DeleteException(DeleteMessageUtils.DELETE_PRODUCT_FAILED);
        }
        product.getImages().forEach(image -> imageService.deleteById(image.getId()));
        productRepository.removeById(product.getId());
    }

    @Override
    public void deleteImagesByProductId(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.PRODUCT_ID_NOT_FOUND));
        product.getImages().forEach(image -> imageService.deleteById(image.getId()));
    }

    @Override
    public Product.Color[] findAllColor() {
        return Product.Color.values();
    }

    private void checkProductNameIsUnique(ProductUpdateDTO productUpdateDTO, String name) {
        if (productRepository.getByName(productUpdateDTO.getName()).isPresent()
                && !productUpdateDTO.getName().equals(name))
            throw new DuplicateException(DuplicateMessageUtils.PRODUCT_NAME_DUPLICATE);
    }

    private String generateCode(String subcategoryCode) {
        StringBuilder builder = new StringBuilder();
        int size = productRepository.countProductBySubcategory(subcategoryCode);
        return builder.append(subcategoryCode)
                .append("#")
                .append(String.format("%03d", size + 1))
                .toString();
    }

    private String uploadAvatar(MultipartFile file) {
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String uploadedFileName = UUID.randomUUID() + "." + extension;
            Path destinationFile = this.root.resolve(Paths.get(uploadedFileName)).normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return MvcUriComponentsBuilder
                    .fromMethodName(FileRestController.class, "downloadFile", uploadedFileName)
                    .build()
                    .toString();
        } catch (IOException e) {
            throw new ResourceNotFoundException(FileExceptionMessageUtils.UPLOAD_IMAGE_ERROR + " " + e.getMessage());
        }
    }

    private void deleteFileFromSystem(String avatarURL) {
        String[] fileName = avatarURL.split("/");
        try {
            Path path = root.resolve(Objects.requireNonNull(fileName[6]));
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new ResourceNotFoundException(FileExceptionMessageUtils.DELETE_ERROR_FROM_ROOT + " " + e.getMessage());
        }
    }
}
