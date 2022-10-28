package cybersoft.javabackend.java18.obajuecommerce.app_category.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.service.CategoryService;
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
@SecurityRequirement(name = "bearerAuth")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDTO> findCategories() {
        return ResponseUtils.get(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> findCategoryById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody @Valid CategoryCreateDTO categoryCreateDTO) {
        return ResponseUtils.get(categoryService.save(categoryCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseDTO> deleteCategoryById(@PathVariable("id") UUID id) {
        categoryService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_CATEGORY_SUCCESS, HttpStatus.OK);
    }
}
