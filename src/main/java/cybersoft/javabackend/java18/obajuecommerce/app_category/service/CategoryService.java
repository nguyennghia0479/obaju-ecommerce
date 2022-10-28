package cybersoft.javabackend.java18.obajuecommerce.app_category.service;

import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(UUID id);

    CategoryCreateDTO save(CategoryCreateDTO categoryCreateDTO);

    void deleteById(UUID id);
}
