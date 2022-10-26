package cybersoft.javabackend.java18.obajuecommerce.role.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RoleRestController {
    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<ResponseDTO> findRoles() {
        return ResponseUtils.get(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles/{code}")
    public ResponseEntity<ResponseDTO> findRoleByCode(@PathVariable("code") String code) {
        return ResponseUtils.get(roleService.findByCode(code), HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<ResponseDTO> createRole(@RequestBody @Valid RoleCreateDTO roleCreateDTO) {
        return ResponseUtils.get(roleService.save(roleCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/roles/{code}")
    public ResponseEntity<ResponseDTO> deleteRoleByCode(@PathVariable("code") String code) {
        roleService.deleteByCode(code);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_ROLE_SUCCESS, HttpStatus.OK);
    }
}
