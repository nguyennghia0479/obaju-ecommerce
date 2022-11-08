package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.UserNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.UserNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.RegisterDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.jwt.JwtUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.mapper.UserMapper;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserGroupRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final UserGroupRepository userGroupRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userGroupRepository = userGroupRepository;
    }


    @Override
    public String login(LoginDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundMessageUtils.USERNAME_NOT_FOUND));
        if (passwordEncoder.matches(dto.getPassword(), (user.getPassword())))
            return jwtUtils.generateJwt(user);
        throw new UserNotFoundException(UserNotFoundMessageUtils.PASSWORD_NOT_MATCH);
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        User newUser = UserMapper.INSTANCE.registerDTOToUser(registerDTO);
        newUser.setStatus(User.Status.ACTIVE);
        User userCreate = userRepository.save(newUser);
        addUserToUserGroup(userCreate);
        return jwtUtils.generateJwt(userCreate);
    }

    private void addUserToUserGroup(User user) {
        Set<UserGroup> userGroups = new LinkedHashSet<>();
        user.setUserGroups(userGroups);
        Optional<UserGroup> userGroup = userGroupRepository.findByName("GROUP USER");
        userGroup.ifPresent(ug -> ug.addUser(user));
    }
}
