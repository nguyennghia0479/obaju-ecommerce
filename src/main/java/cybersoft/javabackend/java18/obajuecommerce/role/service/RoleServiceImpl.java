package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.mapper.RoleMapper;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper.INSTANCE::roleToRoleDTO)
                .toList();
    }

    @Override
    public RoleDTO findByCode(String code) {
        return roleRepository.findByCode(code)
                .map(RoleMapper.INSTANCE::roleToRoleDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ROLE_CODE_NOT_FOUND));
    }

    @Override
    public RoleCreateDTO save(RoleCreateDTO roleCreateDTO) {
        Role roleCreate = RoleMapper.INSTANCE.roleCreateDTOToRole(roleCreateDTO);
       roleRepository.save(roleCreate);
       return RoleMapper.INSTANCE.roleToRoleCreateDTO(roleCreate);
    }

    @Override
    public void deleteByCode(String code) {
       roleRepository.findByCode(code)
               .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ROLE_CODE_NOT_FOUND));
       roleRepository.deleteByCode(code);
    }
}
