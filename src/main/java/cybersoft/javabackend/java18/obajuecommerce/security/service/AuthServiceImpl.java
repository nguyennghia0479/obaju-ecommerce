package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.DuplicateException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.UserNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DuplicateMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.UserNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.RegisterDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.UserUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.jwt.JwtUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.mapper.UserMapper;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserGroupRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper.INSTANCE::userToUserDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USERNAME_NOT_FOUND));
    }

    @Override
    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User updateUser = userRepository.findByUsername(userUpdateDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USERNAME_NOT_FOUND));
        checkEmailAndPhoneNumIsUnique(userUpdateDTO.getEmail(), userUpdateDTO.getPhoneNum(), updateUser);
        updateUser.setFullName(userUpdateDTO.getFullName());
        updateUser.setPhoneNum(userUpdateDTO.getPhoneNum());
        updateUser.setEmail(userUpdateDTO.getEmail());
        return UserMapper.INSTANCE.userToUserDTO(updateUser);
    }

    private void addUserToUserGroup(User user) {
        Set<UserGroup> userGroups = new LinkedHashSet<>();
        user.setUserGroups(userGroups);
        Optional<UserGroup> userGroup = userGroupRepository.findByName("GROUP USER");
        userGroup.ifPresent(ug -> ug.addUser(user));
    }

    private void checkEmailAndPhoneNumIsUnique(String email, String phoneNum, User updateUser) {
        List<String> errors = new ArrayList<>();
        if(userRepository.isExistedByEmail(email) && !email.equals(updateUser.getEmail())) {
            errors.add("Email is existed");
        }
        if(userRepository.isExistedByPhoneNum(phoneNum) && !phoneNum.equals(updateUser.getPhoneNum())) {
            errors.add("Phone number is existed");
        }
        if(!errors.isEmpty()) {
            throw new DuplicateException(DuplicateMessageUtils.USER_EMAIL_PHONE_DUPLICATE);
        }
    }
}
