package cybersoft.javabackend.java18.obajuecommerce.user.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.mapper.UserMapper;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        User userCreate = UserMapper.INSTANCE.userCreateDTOToUser(userCreateDTO);
        userRepository.save(userCreate);
        return UserMapper.INSTANCE.userToUserCreateDTO(userCreate);
    }

    @Override
    public void deleteById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.USER_ID_NOT_FOUND));
        userRepository.removeById(user.getId());
    }
}
