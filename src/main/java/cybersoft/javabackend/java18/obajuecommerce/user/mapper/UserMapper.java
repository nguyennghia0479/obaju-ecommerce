package cybersoft.javabackend.java18.obajuecommerce.user.mapper;

import cybersoft.javabackend.java18.obajuecommerce.security.dto.RegisterDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    UserCreateDTO userToUserCreateDTO(User user);

    User userCreateDTOToUser(UserCreateDTO userDTO);

    User registerDTOToUser(RegisterDTO registerDTO);
}
