package cybersoft.javabackend.java18.obajuecommerce.role.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.role.service.RoleService;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class RoleRestController {
    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findRoles() {
        return ResponseUtils.get(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles/include-operations")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findRolesIncludeOperations() {
        return ResponseUtils.get(roleService.findAllIncludeOperationDTO(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    @SecurityOperation(name = OperationUtils.FIND_BY_ID, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findRoleById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(roleService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/roles")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> createRole(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(roleService.save(roleDTO), HttpStatus.CREATED);
    }

    @PostMapping("/roles/{id}/add-operations")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> addOperations(@PathVariable("id") UUID roleId,
                                                     @RequestBody List<UUID> operationIds) {
        return ResponseUtils.get(roleService.addOperations(roleId, operationIds), HttpStatus.CREATED);
    }

    @DeleteMapping("/roles/{id}")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    public ResponseEntity<ResponseDTO> deleteRoleById(@PathVariable("id") UUID id) {
        roleService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_ROLE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}/remove-operations")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    public ResponseEntity<ResponseDTO> removeOperations(@PathVariable("id") UUID roleId,
                                                     @RequestBody List<UUID> operationIds) {
        return ResponseUtils.get(roleService.removeOperations(roleId, operationIds), HttpStatus.OK);
    }
}
