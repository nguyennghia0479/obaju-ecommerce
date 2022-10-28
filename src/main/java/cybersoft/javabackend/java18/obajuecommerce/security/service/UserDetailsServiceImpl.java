package cybersoft.javabackend.java18.obajuecommerce.security.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.UserNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.UserNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundMessageUtils.USERNAME_NOT_FOUND));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
