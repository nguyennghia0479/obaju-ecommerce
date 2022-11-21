package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.RegisterDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.UserUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserDTO;

public interface AuthService {
    String login(LoginDTO dto);

    String register(RegisterDTO registerDTO);

    UserDTO findByUsername(String username);

    UserDTO updateUser(UserUpdateDTO userUpdateDTO);
}
