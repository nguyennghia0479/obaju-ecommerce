package cybersoft.javabackend.java18.obajuecommerce.user.service;

import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> findAll();

    UserDTO findById(UUID id);

    UserCreateDTO save(UserCreateDTO userCreateDTO);

    void deleteById(UUID id);
}
