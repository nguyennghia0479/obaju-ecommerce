package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleIncludeOperationDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.mapper.RoleMapper;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.OperationRepository;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final OperationRepository operationRepository;
    private final UserGroupRepository userGroupRepository;

    public RoleServiceImpl(RoleRepository roleRepository, OperationRepository operationRepository, UserGroupRepository userGroupRepository) {
        this.roleRepository = roleRepository;
        this.operationRepository = operationRepository;
        this.userGroupRepository = userGroupRepository;
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
        List<Operation> operations = operationRepository.findAll();
        List<UserGroup> userGroups = userGroupRepository.findAll();
        operations.forEach(role::removeOperation);
        userGroups.forEach(ug -> ug.removeRole(role));
        roleRepository.removeById(role.getId());
    }

    @Override
    public RoleIncludeOperationDTO addOperations(UUID roleId, List<UUID> operationsIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ROLE_ID_NOT_FOUND));
        List<Operation> operations = operationRepository.findAllById(operationsIds);
        operations.forEach(role::addOperation);
        return RoleMapper.INSTANCE.roleToRoleIncludeOperationDTO(role);
    }

    @Override
    public RoleIncludeOperationDTO removeOperations(UUID roleId, List<UUID> operationsIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.ROLE_ID_NOT_FOUND));
        List<Operation> operations = operationRepository.findAllById(operationsIds);
        operations.forEach(role::removeOperation);
        return RoleMapper.INSTANCE.roleToRoleIncludeOperationDTO(role);
    }

    @Override
    public List<RoleIncludeOperationDTO> findAllIncludeOperationDTO() {
        return roleRepository.findAllIncludeOperation()
                .stream()
                .distinct()
                .map(RoleMapper.INSTANCE::roleToRoleIncludeOperationDTO)
                .toList();
    }
}
