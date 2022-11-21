package cybersoft.javabackend.java18.obajuecommerce.app_product_size.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.dto.ProductSizeCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.service.ProductSizeService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
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

    @GetMapping("product-sizes/select-size/{productId}")
    public ResponseEntity<ResponseDTO> getSelectSizeByProductId(@PathVariable("productId") UUID id) {
        return ResponseUtils.get(productSizeService.findByProductId(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("product-sizes")
    public ResponseEntity<ResponseDTO> findAllProductSizes() {
        return ResponseUtils.get(productSizeService.findAll(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    @PostMapping("/admin/product-sizes")
    public ResponseEntity<ResponseDTO> createProductSize(@RequestBody @Valid ProductSizeCreateDTO productSizeCreateDTO) {
        return ResponseUtils.get(productSizeService.save(productSizeCreateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    @DeleteMapping("/admin/product-sizes/{id}")
    public ResponseEntity<ResponseDTO> deleteProductSizeById(@PathVariable("id") UUID id) {
        productSizeService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_PRODUCT_SIZE_SUCCESS, HttpStatus.OK);
    }
}
