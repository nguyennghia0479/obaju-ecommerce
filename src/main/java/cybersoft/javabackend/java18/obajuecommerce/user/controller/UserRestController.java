package cybersoft.javabackend.java18.obajuecommerce.user.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> findUsers() {
        return ResponseUtils.get(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> findUserById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseUtils.get(userService.save(userCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_USER_SUCCESS, HttpStatus.OK);
    }
}
