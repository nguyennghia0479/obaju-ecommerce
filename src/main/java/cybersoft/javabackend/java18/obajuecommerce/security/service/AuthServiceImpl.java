package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.UserNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.UserNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.jwt.JwtUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public String login(LoginDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException(UserNotFoundMessageUtils.USERNAME_NOT_FOUND)
                );
        if(passwordEncoder.matches(dto.getPassword(),(user.getPassword())))
            return jwtUtils.generateJwt(user.getUsername());
        throw new UserNotFoundException(UserNotFoundMessageUtils.PASSWORD_NOT_MATCH);
    }
}
