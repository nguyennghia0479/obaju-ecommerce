package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.service;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryIncludeProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;

import java.util.List;
import java.util.UUID;

public interface SubcategoryService {
    List<SubcategoryIncludeProductDTO> findAllIncludeProductDTO();

    List<SubcategoryDTO> findAll();

    SubcategoryIncludeProductDTO findById(UUID id);

    SubcategoryDTO save(SubcategoryCreateDTO subcategoryCreateDTO);

    SubcategoryDTO update(SubcategoryUpdateDTO subcategoryUpdateDTO);

    void deleteById(UUID id);

    Subcategory.Category[] findAllCategory();
}
