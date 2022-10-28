package cybersoft.javabackend.java18.obajuecommerce.user.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.mapper.UserMapper;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserGroupRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserGroupRepository userGroupRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .toList();
    }

    @Override
    public UserDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::userToUserDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_ID_NOT_FOUND));
    }

    @Override
    public UserCreateDTO save(UserCreateDTO userCreateDTO) {
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        User userCreate = UserMapper.INSTANCE.userCreateDTOToUser(userCreateDTO);
        userCreate.setStatus(User.Status.ACTIVE);
        userRepository.save(userCreate);
        return UserMapper.INSTANCE.userToUserCreateDTO(userCreate);
    }

    @Override
    public void deleteById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_ID_NOT_FOUND));
        List<UserGroup> userGroups = userGroupRepository.findAll();
        userGroups.forEach(ug -> ug.removeUser(user));
        userRepository.removeById(user.getId());
    }
}
