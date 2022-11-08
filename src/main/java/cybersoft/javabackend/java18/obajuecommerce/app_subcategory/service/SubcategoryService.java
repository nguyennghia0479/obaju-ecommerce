package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.service;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.*;

import java.util.List;
import java.util.UUID;

public interface SubcategoryService {
    List<SubcategoryIncludeProductDTO> findAllIncludeProductDTO();
    List<SubcategoryIncludeCategoryDTO> findAllIncludeCategoryDTO();

    List<SubcategoryDTO> findAll();

    SubcategoryIncludeProductDTO findById(UUID id);

    SubcategoryDTO save(SubcategoryCreateDTO subcategoryCreateDTO);

    SubcategoryDTO update(SubcategoryUpdateDTO subcategoryUpdateDTO);

    void deleteById(UUID id);
}
