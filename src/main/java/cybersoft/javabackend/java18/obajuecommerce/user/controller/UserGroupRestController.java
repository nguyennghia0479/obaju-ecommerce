package cybersoft.javabackend.java18.obajuecommerce.user.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.service.UserGroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class UserGroupRestController {
    private final UserGroupService userGroupService;

    public UserGroupRestController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @GetMapping("/usergroups")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findUserGroups() {
        return ResponseUtils.get(userGroupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/usergroups/include-users")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findUserGroupsIncludeUsers() {
        return ResponseUtils.get(userGroupService.findAllUserGroupIncludeUserDTO(), HttpStatus.OK);
    }

    @GetMapping("/usergroups/include-roles")
    @SecurityOperation(name = OperationUtils.FIND_ALL, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findUserGroupsIncludeRoles() {
        return ResponseUtils.get(userGroupService.findAllUserGroupIncludeRoleDTO(), HttpStatus.OK);
    }

    @GetMapping("/usergroups/{id}")
    @SecurityOperation(name = OperationUtils.FIND_BY_ID, type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findUserGroupById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(userGroupService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/usergroups")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> createUserGroup(@RequestBody @Valid UserGroupDTO userGroupDTO) {
        return ResponseUtils.get(userGroupService.save(userGroupDTO), HttpStatus.CREATED);
    }

    @PostMapping("/usergroups/{id}/add-users")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> addUsers(@PathVariable("id") UUID userGroupId,
                                               @RequestBody List<UUID> userIds) {
        return ResponseUtils.get(userGroupService.addUsers(userGroupId, userIds), HttpStatus.CREATED);
    }

    @PostMapping("/usergroups/{id}/add-roles")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> addRoles(@PathVariable("id") UUID userGroupId,
                                                @RequestBody List<UUID> roleIds) {
        return ResponseUtils.get(userGroupService.addRoles(userGroupId, roleIds), HttpStatus.CREATED);
    }

    @DeleteMapping("/usergroups/{id}")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    public ResponseEntity<ResponseDTO> deleteUserGroupById(@PathVariable("id") UUID id) {
        userGroupService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_USER_GROUP_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/usergroups/{id}/remove-users")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    public ResponseEntity<ResponseDTO> removeUsers(@PathVariable("id") UUID userGroupId,
                                               @RequestBody List<UUID> userIds) {
        return ResponseUtils.get(userGroupService.removeUsers(userGroupId, userIds), HttpStatus.OK);
    }

    @DeleteMapping("/usergroups/{id}/remove-roles")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    public ResponseEntity<ResponseDTO> removeRoles(@PathVariable("id") UUID userGroupId,
                                                   @RequestBody List<UUID> roleIds) {
        return ResponseUtils.get(userGroupService.removeRoles(userGroupId, roleIds), HttpStatus.OK);
    }
}
