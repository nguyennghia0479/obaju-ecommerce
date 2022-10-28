package cybersoft.javabackend.java18.obajuecommerce.user.mapper;

import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupIncludeRoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupIncludeUserDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserGroupMapper {
    UserGroupMapper INSTANCE = Mappers.getMapper(UserGroupMapper.class);

    UserGroupDTO userGroupToUserGroupDTO(UserGroup userGroup);

    UserGroup userGroupDTOToUserGroup(UserGroupDTO userGroupDTO);

    UserGroupIncludeUserDTO userGroupToUserGroupIncludeUserDTO(UserGroup userGroup);

    UserGroupIncludeRoleDTO userGroupToUserGroupIncludeRoleDTO(UserGroup userGroup);
}
