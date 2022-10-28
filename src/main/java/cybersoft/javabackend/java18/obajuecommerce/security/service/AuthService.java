package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO dto);

    String register(RegisterDTO registerDTO);
}
