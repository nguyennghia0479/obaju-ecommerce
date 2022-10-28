package cybersoft.javabackend.java18.obajuecommerce.app_category.mapper;

import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    CategoryCreateDTO categoryToCategoryCreateDTO(Category category);

    Category categoryCreateDTOToCategory(CategoryCreateDTO categoryCreateDTO);
}
