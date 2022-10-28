package cybersoft.javabackend.java18.obajuecommerce.security.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.LoginDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.dto.RegisterDTO;
import cybersoft.javabackend.java18.obajuecommerce.security.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthRestController {
    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO dto) {
        return ResponseUtils.get(authService.login(dto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterDTO dto) {
        return ResponseUtils.get(authService.register(dto), HttpStatus.OK);
    }
}
