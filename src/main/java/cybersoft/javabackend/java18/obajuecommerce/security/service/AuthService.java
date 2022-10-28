package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO dto);
}
