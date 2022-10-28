package cybersoft.javabackend.java18.obajuecommerce.user.service;

import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupIncludeRoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupIncludeUserDTO;

import java.util.List;
import java.util.UUID;

public interface UserGroupService {
    List<UserGroupDTO> findAll();

    UserGroupDTO findById(UUID id);

    UserGroupDTO save(UserGroupDTO userGroupDTO);

    void deleteById(UUID id);

    UserGroupIncludeUserDTO addUsers(UUID userGroupId, List<UUID> userIds);

    UserGroupIncludeUserDTO removeUsers(UUID userGroupId, List<UUID> userIds);

    List<UserGroupIncludeUserDTO> findAllUserGroupIncludeUserDTO();

    UserGroupIncludeRoleDTO addRoles(UUID userGroupId, List<UUID> roleIds);

    UserGroupIncludeRoleDTO removeRoles(UUID userGroupId, List<UUID> roleIds);

    List<UserGroupIncludeRoleDTO> findAllUserGroupIncludeRoleDTO();
}
