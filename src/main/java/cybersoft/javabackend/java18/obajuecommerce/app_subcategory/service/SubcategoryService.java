package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.service;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryIncludeProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface SubcategoryService {
    List<SubcategoryIncludeProductDTO> findAll();

    SubcategoryIncludeProductDTO findById(UUID id);

    SubcategoryDTO save(SubcategoryCreateDTO subcategoryCreateDTO);

    SubcategoryDTO update(SubcategoryUpdateDTO subcategoryUpdateDTO);

    void deleteById(UUID id);
}
