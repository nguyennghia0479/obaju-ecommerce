package cybersoft.javabackend.java18.obajuecommerce.user.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupIncludeRoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupIncludeUserDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.mapper.UserGroupMapper;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserGroupRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserGroupDTO> findAll() {
        return userGroupRepository.findAll()
                .stream()
                .map(UserGroupMapper.INSTANCE::userGroupToUserGroupDTO)
                .toList();
    }

    @Override
    public UserGroupDTO findById(UUID id) {
        return userGroupRepository.findById(id)
                .map(UserGroupMapper.INSTANCE::userGroupToUserGroupDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_GROUP_ID_NOT_FOUND));
    }

    @Override
    public UserGroupDTO save(UserGroupDTO userGroupDTO) {
        UserGroup userGroupCreate = UserGroupMapper.INSTANCE.userGroupDTOToUserGroup(userGroupDTO);
        userGroupRepository.save(userGroupCreate);
        return UserGroupMapper.INSTANCE.userGroupToUserGroupDTO(userGroupCreate);
    }

    @Override
    public void deleteById(UUID id) {
        UserGroup userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_GROUP_ID_NOT_FOUND));
        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        users.forEach(userGroup::removeUser);
        roles.forEach(userGroup::removeRole);
        userGroupRepository.removeById(userGroup.getId());
    }

    @Override
    public UserGroupIncludeUserDTO addUsers(UUID userGroupId, List<UUID> userIds) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_GROUP_ID_NOT_FOUND));
        List<User> users = userRepository.findAllById(userIds);
        users.forEach(userGroup::addUser);
        return UserGroupMapper.INSTANCE.userGroupToUserGroupIncludeUserDTO(userGroup);
    }

    @Override
    public UserGroupIncludeUserDTO removeUsers(UUID userGroupId, List<UUID> userIds) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_GROUP_ID_NOT_FOUND));
        List<User> users = userRepository.findAllById(userIds);
        users.forEach(userGroup::removeUser);
        return UserGroupMapper.INSTANCE.userGroupToUserGroupIncludeUserDTO(userGroup);
    }

    @Override
    public List<UserGroupIncludeUserDTO> findAllUserGroupIncludeUserDTO() {
        return userGroupRepository.findAllIncludeUser()
                .stream()
                .distinct()
                .map(UserGroupMapper.INSTANCE::userGroupToUserGroupIncludeUserDTO)
                .toList();
    }

    @Override
    public UserGroupIncludeRoleDTO addRoles(UUID userGroupId, List<UUID> roleIds) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_GROUP_ID_NOT_FOUND));
        List<Role> roles = roleRepository.findAllById(roleIds);
        roles.forEach(userGroup::addRole);
        return UserGroupMapper.INSTANCE.userGroupToUserGroupIncludeRoleDTO(userGroup);
    }

    @Override
    public UserGroupIncludeRoleDTO removeRoles(UUID userGroupId, List<UUID> roleIds) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_GROUP_ID_NOT_FOUND));
        List<Role> roles = roleRepository.findAllById(roleIds);
        roles.forEach(userGroup::removeRole);
        return UserGroupMapper.INSTANCE.userGroupToUserGroupIncludeRoleDTO(userGroup);
    }

    @Override
    public List<UserGroupIncludeRoleDTO> findAllUserGroupIncludeRoleDTO() {
        return userGroupRepository.findAllIncludeRole()
                .stream()
                .distinct()
                .map(UserGroupMapper.INSTANCE::userGroupToUserGroupIncludeRoleDTO)
                .toList();
    }
}
