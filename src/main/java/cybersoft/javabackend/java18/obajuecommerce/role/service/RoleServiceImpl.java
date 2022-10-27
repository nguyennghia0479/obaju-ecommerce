package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.mapper.RoleMapper;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public RoleDTO findById(UUID id) {
        return roleRepository.findById(id)
                .map(RoleMapper.INSTANCE::roleToRoleDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ROLE_ID_NOT_FOUND));
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role roleCreate = RoleMapper.INSTANCE.roleDTOToRole(roleDTO);
        roleRepository.save(roleCreate);
        return RoleMapper.INSTANCE.roleToRoleDTO(roleCreate);
    }

    @Override
    public void deleteById(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ROLE_ID_NOT_FOUND));
        roleRepository.removeById(role.getId());
    }
}
