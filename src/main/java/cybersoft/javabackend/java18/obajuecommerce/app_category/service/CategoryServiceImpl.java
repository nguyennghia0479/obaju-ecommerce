package cybersoft.javabackend.java18.obajuecommerce.app_category.service;

import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.dto.CategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_category.mapper.CategoryMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_category.model.Category;
import cybersoft.javabackend.java18.obajuecommerce.app_category.repository.CategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.getAll()
                .stream()
                .distinct()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .toList();
    }

    @Override
    public CategoryDTO findById(UUID id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.CATEGORY_ID_NOT_FOUND));
    }

    @Override
    public CategoryCreateDTO save(CategoryCreateDTO categoryCreateDTO) {
        Category categoryCreate = CategoryMapper.INSTANCE.categoryCreateDTOToCategory(categoryCreateDTO);
        categoryRepository.save(categoryCreate);
        return CategoryMapper.INSTANCE.categoryToCategoryCreateDTO(categoryCreate);
    }

    @Override
    public void deleteById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.CATEGORY_ID_NOT_FOUND));
        categoryRepository.removeById(category.getId());
    }
}
