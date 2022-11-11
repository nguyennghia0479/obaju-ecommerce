package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.mapper;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryIncludeCategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryIncludeProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubcategoryMapper {
    SubcategoryMapper INSTANCE = Mappers.getMapper(SubcategoryMapper.class);

    SubcategoryDTO subcategoryToSubcategoryDTO(Subcategory subcategory);

    SubcategoryIncludeCategoryDTO subcategoryToSubcategoryIncludeCategoryDTO(Subcategory subcategory);

    SubcategoryIncludeProductDTO subcategoryToSubcategoryIncludeProductDTO(Subcategory subcategory);

    Subcategory subcategoryCreateDTOToSubcategory(SubcategoryCreateDTO subcategoryCreateDTO);
}
