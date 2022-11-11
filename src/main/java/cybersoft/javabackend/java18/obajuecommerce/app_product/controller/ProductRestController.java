package cybersoft.javabackend.java18.obajuecommerce.app_product.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.service.ProductService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/select-colors")
    public ResponseEntity<ResponseDTO> getColors() {
        return ResponseUtils.get(productService.findAllColor(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseDTO> findProducts() {
        return ResponseUtils.get(productService.findAllIncludeSubcategoryDTO(), HttpStatus.OK);
    }

    @GetMapping("/select-product")
    public ResponseEntity<ResponseDTO> getSelectProduct(@RequestParam(value = "subcategoryId", required = false) UUID id) {
        if(id != null)
            return ResponseUtils.get(productService.findAllBySubcategoryId(id), HttpStatus.OK);
        return ResponseUtils.get(null, HttpStatus.OK);
    }

    @GetMapping("/products/subcategory/{subcategory-name}")
    public ResponseEntity<ResponseDTO> findProductsBySubcategoryName(@PathVariable("subcategory-name") String name) {
        return ResponseUtils.get(productService.findAllBySubcategoryName(name), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> findProductById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<ResponseDTO> findProductByName(@PathVariable("name") String name) {
        return ResponseUtils.get(productService.findByName(name), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value = "/admin/products", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseDTO> createProduct(@ModelAttribute @Valid ProductCreateDTO productCreateDTO, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseUtils.error(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseUtils.get(productService.save(productCreateDTO), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(value = "/admin/products", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute @Valid ProductUpdateDTO productUpdateDTO, BindingResult result) {
        return ResponseUtils.get(productService.update(productUpdateDTO), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ResponseDTO> deleteProductById(@PathVariable("id") UUID id) {
        productService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_PRODUCT_SUCCESS, HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/admin/products/{id}/images")
    public ResponseEntity<ResponseDTO> deleteImagesByProductId(@PathVariable("id") UUID id) {
        productService.deleteImagesByProductId(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_PRODUCT_IMAGES_SUCCESS, HttpStatus.OK);
    }
}
