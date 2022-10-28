package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.service;

import cybersoft.javabackend.java18.obajuecommerce.app_category.model.Category;
import cybersoft.javabackend.java18.obajuecommerce.app_category.repository.CategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.mapper.SubcategoryMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.repository.SubcategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<SubcategoryDTO> findAll() {
        return subcategoryRepository.findAll()
                .stream()
                .map(SubcategoryMapper.INSTANCE::subcategoryToSubcategoryDTO)
                .toList();
    }

    @Override
    public SubcategoryDTO findById(UUID id) {
        return subcategoryRepository.findById(id)
                .map(SubcategoryMapper.INSTANCE::subcategoryToSubcategoryDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.SUBCATEGORY_ID_NOT_FOUND));
    }

    @Override
    public SubcategoryDTO save(SubcategoryCreateDTO subcategoryCreateDTO) {
        Category category = categoryRepository.findById(subcategoryCreateDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.CATEGORY_ID_NOT_FOUND));
        Subcategory subcategoryCreate = SubcategoryMapper.INSTANCE.subcategoryCreateDTOToSubcategory(subcategoryCreateDTO);
        subcategoryCreate.setCode(generateCode(category.getCode()));
        subcategoryCreate.setCategory(category);
        subcategoryRepository.save(subcategoryCreate);
        return SubcategoryMapper.INSTANCE.subcategoryToSubcategoryDTO(subcategoryCreate);
    }

    @Override
    public SubcategoryDTO update(SubcategoryUpdateDTO subcategoryUpdateDTO) {
        Subcategory subcategoryUpdate = subcategoryRepository.findById(subcategoryUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.SUBCATEGORY_ID_NOT_FOUND));
        subcategoryUpdate.setDescription(subcategoryUpdateDTO.getDescription());
        return SubcategoryMapper.INSTANCE.subcategoryToSubcategoryDTO(subcategoryUpdate);
    }

    @Override
    public void deleteById(UUID id) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.SUBCATEGORY_ID_NOT_FOUND));
        subcategoryRepository.removeById(subcategory.getId());
    }

    private String generateCode(String categoryCode) {
        StringBuilder builder = new StringBuilder();
        int size = subcategoryRepository.countSubcategoryByCategoryCode(categoryCode);
        return builder.append(categoryCode).append(String.format("%02d", size + 1)).toString();
    }
}
