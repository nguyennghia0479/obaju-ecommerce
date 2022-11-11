package cybersoft.javabackend.java18.obajuecommerce.app_product_size.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.service.ProductSizeService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ProductSizeRestController {
    private final ProductSizeService productSizeService;

    public ProductSizeRestController(ProductSizeService productSizeService) {
        this.productSizeService = productSizeService;
    }

    @GetMapping("product-sizes")
    public ResponseEntity<ResponseDTO> findAllProductSizes() {
        return ResponseUtils.get(productSizeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("product-sizes/{size-type}")
    public ResponseEntity<ResponseDTO> findAllProductSizesByType(@PathVariable("size-type") ProductSize.SizeType sizeType) {
        return ResponseUtils.get(productSizeService.findBySizeType(sizeType), HttpStatus.OK);
    }

    @GetMapping("product-sizes/select-size-type")
    public ResponseEntity<ResponseDTO> getSelectSizeType() {
        return ResponseUtils.get(productSizeService.findAllSizeType(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/admin/product-sizes")
    public ResponseEntity<ResponseDTO> createProductSize(@RequestBody @Valid ProductSizeCreateDTO productSizeCreateDTO) {
        return ResponseUtils.get(productSizeService.save(productSizeCreateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/admin/product-sizes/{id}")
    public ResponseEntity<ResponseDTO> deleteProductSizeById(@PathVariable("id") UUID id) {
        productSizeService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_PRODUCT_SIZE_SUCCESS, HttpStatus.OK);
    }
}
