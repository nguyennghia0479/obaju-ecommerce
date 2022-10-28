package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.mapper;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubcategoryMapper {
    SubcategoryMapper INSTANCE = Mappers.getMapper(SubcategoryMapper.class);

    SubcategoryDTO subcategoryToSubcategoryDTO(Subcategory subcategory);

    Subcategory subcategoryCreateDTOToSubcategory(SubcategoryCreateDTO subcategoryCreateDTO);

    Subcategory subcategoryUpdateDTOToSubcategory(SubcategoryUpdateDTO subcategoryUpdateDTO);
}
