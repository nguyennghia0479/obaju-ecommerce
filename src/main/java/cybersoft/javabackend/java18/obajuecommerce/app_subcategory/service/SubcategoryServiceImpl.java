package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.service;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryIncludeProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.dto.SubcategoryUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.mapper.SubcategoryMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.repository.SubcategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.DeleteException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ConvertUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public List<SubcategoryIncludeProductDTO> findAllIncludeProductDTO() {
        return subcategoryRepository.findAllIncludeProducts()
                .stream()
                .map(SubcategoryMapper.INSTANCE::subcategoryToSubcategoryIncludeProductDTO)
                .toList();
    }

    @Override
    public List<SubcategoryDTO> findAll() {
        Sort sort = Sort.by("lastModifiedAt").descending();
        return subcategoryRepository.findAll(sort)
                .stream()
                .map(SubcategoryMapper.INSTANCE::subcategoryToSubcategoryDTO)
                .toList();
    }

    @Override
    public SubcategoryIncludeProductDTO findById(UUID id) {
        return subcategoryRepository.findById(id)
                .map(SubcategoryMapper.INSTANCE::subcategoryToSubcategoryIncludeProductDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.SUBCATEGORY_ID_NOT_FOUND));
    }

    @Override
    public SubcategoryDTO save(SubcategoryCreateDTO subcategoryCreateDTO) {
        Subcategory subcategoryCreate = SubcategoryMapper.INSTANCE.subcategoryCreateDTOToSubcategory(subcategoryCreateDTO);
        subcategoryCreate.setNameURL(ConvertUtils.convertStringToURL(subcategoryCreate.getName()));
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
       if(!subcategory.getProducts().isEmpty())
           throw new DeleteException(DeleteMessageUtils.DELETE_SUBCATEGORY_FAILED);
       subcategoryRepository.removeById(subcategory.getId());
    }

    @Override
    public Subcategory.Category[] findAllCategory() {
        return Subcategory.Category.values();
    }
}
