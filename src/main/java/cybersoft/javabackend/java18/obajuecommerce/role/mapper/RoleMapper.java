package cybersoft.javabackend.java18.obajuecommerce.role.mapper;

import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);

    RoleCreateDTO roleToRoleCreateDTO(Role role);

    Role roleCreateDTOToRole(RoleCreateDTO roleCreateDTO);
}
