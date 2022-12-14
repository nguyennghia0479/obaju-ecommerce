package cybersoft.javabackend.java18.obajuecommerce.user.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findUsers() {
        return ResponseUtils.get(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @SecurityOperation(name = OperationUtils.FIND_BY_ID, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findUserById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseUtils.get(userService.save(userCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_USER_SUCCESS, HttpStatus.OK);
    }
}
