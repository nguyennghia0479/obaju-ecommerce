package cybersoft.javabackend.java18.obajuecommerce.app_product.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_product.service.ProductService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DateTimeUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private List<Order> getOrder(String[] sort) {
        List<Order> orders = new ArrayList<Order>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }
        return orders;
    }

    @GetMapping("/products/select-color")
    public ResponseEntity<ResponseDTO> getColors() {
        return ResponseUtils.get(productService.findAllColor(), HttpStatus.OK);
    }

    @GetMapping("/subcategories/products/select-products")
    public ResponseEntity<ResponseDTO> getProductBySubcategoryId(@RequestParam(value = "subcategoryId", required = false) UUID id) {
        if (id != null)
            return ResponseUtils.get(productService.findAllBySubcategoryId(id), HttpStatus.OK);
        return ResponseUtils.get(null, HttpStatus.OK);
    }

    @GetMapping("/products/export-excel")
    public ResponseEntity<Resource> exportFileExcel(@RequestParam(value = "size", defaultValue = "2") int size,
                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "id,desc") String[] sort) {
        List<Order> orders = getOrder(sort);
        String currentDateTime = DateTimeUtils.now();
        String filename = "products_" + currentDateTime + ".xlsx";
        InputStreamResource file = new InputStreamResource(productService.exportExcel(page, size, Sort.by(orders)));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseDTO> findProducts(@RequestParam(value = "size", defaultValue = "2") int size,
                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "id,desc") String[] sort) {
        List<Order> orders = getOrder(sort);
        return ResponseUtils.get(productService.findAllIncludeSubcategoryDTO(page, size, Sort.by(orders)), HttpStatus.OK);
    }

    @GetMapping("/products/subcategory/{subcategory-name}")
    public ResponseEntity<ResponseDTO> findProductsBySubcategoryName(@PathVariable("subcategory-name") String name,
                                                                     @RequestParam(value = "size", defaultValue = "2") int size,
                                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "id,desc") String[] sort) {
        List<Order> orders = getOrder(sort);
        return ResponseUtils.get(productService.findAllBySubcategoryName(name, page, size, Sort.by(orders)), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> findProductById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/products/detail/{name}")
    public ResponseEntity<ResponseDTO> findProductByName(@PathVariable("name") String name) {
        return ResponseUtils.get(productService.findByName(name), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    @PostMapping(value = "/admin/products", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseDTO> createProduct(@ModelAttribute @Valid ProductCreateDTO productCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtils.error(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseUtils.get(productService.save(productCreateDTO), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.UPDATE, type = Operation.Type.SAVE_OR_UPDATE)
    @PutMapping(value = "/admin/products", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute @Valid ProductUpdateDTO productUpdateDTO, BindingResult result) {
        return ResponseUtils.get(productService.update(productUpdateDTO), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ResponseDTO> deleteProductById(@PathVariable("id") UUID id) {
        productService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_PRODUCT_SUCCESS, HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    @DeleteMapping("/admin/products/{id}/images")
    public ResponseEntity<ResponseDTO> deleteImagesByProductId(@PathVariable("id") UUID id) {
        productService.deleteImagesByProductId(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_PRODUCT_IMAGES_SUCCESS, HttpStatus.OK);
    }
}
