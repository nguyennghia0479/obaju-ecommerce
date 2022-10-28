package cybersoft.javabackend.java18.obajuecommerce.user.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.user.dto.UserGroupDTO;
import cybersoft.javabackend.java18.obajuecommerce.user.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserGroupRestController {
    private final UserGroupService userGroupService;

    public UserGroupRestController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @GetMapping("/usergroups")
    public ResponseEntity<ResponseDTO> findUserGroups() {
        return ResponseUtils.get(userGroupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/usergroups/include-users")
    public ResponseEntity<ResponseDTO> findUserGroupsIncludeUsers() {
        return ResponseUtils.get(userGroupService.findAllUserGroupIncludeUserDTO(), HttpStatus.OK);
    }

    @GetMapping("/usergroups/include-roles")
    public ResponseEntity<ResponseDTO> findUserGroupsIncludeRoles() {
        return ResponseUtils.get(userGroupService.findAllUserGroupIncludeRoleDTO(), HttpStatus.OK);
    }

    @GetMapping("/usergroups/{id}")
    public ResponseEntity<ResponseDTO> findUserGroupById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(userGroupService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/usergroups")
    public ResponseEntity<ResponseDTO> createUserGroup(@RequestBody @Valid UserGroupDTO userGroupDTO) {
        return ResponseUtils.get(userGroupService.save(userGroupDTO), HttpStatus.CREATED);
    }

    @PostMapping("/usergroups/{id}/add-users")
    public ResponseEntity<ResponseDTO> addUsers(@PathVariable("id") UUID userGroupId,
                                               @RequestBody List<UUID> userIds) {
        return ResponseUtils.get(userGroupService.addUsers(userGroupId, userIds), HttpStatus.CREATED);
    }

    @PostMapping("/usergroups/{id}/add-roles")
    public ResponseEntity<ResponseDTO> addRoles(@PathVariable("id") UUID userGroupId,
                                                @RequestBody List<UUID> roleIds) {
        return ResponseUtils.get(userGroupService.addRoles(userGroupId, roleIds), HttpStatus.CREATED);
    }

    @DeleteMapping("/usergroups/{id}")
    public ResponseEntity<ResponseDTO> deleteUserGroupById(@PathVariable("id") UUID id) {
        userGroupService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_USER_GROUP_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/usergroups/{id}/remove-users")
    public ResponseEntity<ResponseDTO> removeUsers(@PathVariable("id") UUID userGroupId,
                                               @RequestBody List<UUID> userIds) {
        return ResponseUtils.get(userGroupService.removeUsers(userGroupId, userIds), HttpStatus.OK);
    }

    @DeleteMapping("/usergroups/{id}/remove-roles")
    public ResponseEntity<ResponseDTO> removeRoles(@PathVariable("id") UUID userGroupId,
                                                   @RequestBody List<UUID> roleIds) {
        return ResponseUtils.get(userGroupService.removeRoles(userGroupId, roleIds), HttpStatus.OK);
    }
}
