package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.service.SubcategoryService;
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
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/subcategories/select-category")
    public ResponseEntity<ResponseDTO> getSelectCategory() {
        return ResponseUtils.get(subcategoryService.findAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/subcategories/select-subcategory")
    public ResponseEntity<ResponseDTO> getSelectSubCategories() {
        return ResponseUtils.get(subcategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/subcategories")
    public ResponseEntity<ResponseDTO> findSubcategories() {
        return ResponseUtils.get(subcategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/subcategories/include-products")
    public ResponseEntity<ResponseDTO> findSubcategoriesIncludeProducts() {
        return ResponseUtils.get(subcategoryService.findAllIncludeProductDTO(), HttpStatus.OK);
    }

    @GetMapping("/subcategories/{id}")
    public ResponseEntity<ResponseDTO> findSubcategoryById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(subcategoryService.findById(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    @PostMapping("/admin/subcategories")
    public ResponseEntity<ResponseDTO> createSubcategory(@RequestBody @Valid SubcategoryCreateDTO subcategoryCreateDTO) {
        return ResponseUtils.get(subcategoryService.save(subcategoryCreateDTO), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.UPDATE, type = Operation.Type.SAVE_OR_UPDATE)
    @PutMapping("/admin/subcategories")
    public ResponseEntity<ResponseDTO> updateSubcategory(@RequestBody @Valid SubcategoryUpdateDTO subcategoryUpdateDTO) {
        return ResponseUtils.get(subcategoryService.update(subcategoryUpdateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    @DeleteMapping("/admin/subcategories/{id}")
    public ResponseEntity<ResponseDTO> deleteSubcategoryById(@PathVariable("id") UUID id) {
        subcategoryService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_SUBCATEGORY_SUCCESS, HttpStatus.OK);
    }
}
