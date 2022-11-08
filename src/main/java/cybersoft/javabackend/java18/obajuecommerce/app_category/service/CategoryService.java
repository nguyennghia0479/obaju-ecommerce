package cybersoft.javabackend.java18.obajuecommerce.app_category.service;

import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryIncludeSubcategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> findAll();

    List<CategoryIncludeSubcategoryDTO> findAllSubcategoryDTO();

    CategoryDTO findById(UUID id);

    CategoryDTO save(CategoryCreateDTO categoryCreateDTO);

    void deleteById(UUID id);
}
