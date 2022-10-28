package cybersoft.javabackend.java18.obajuecommerce.role.controller;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.OperationDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.service.OperationService;
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
public class OperationRestController {
    private final OperationService operationService;

    public OperationRestController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/operations")
    public ResponseEntity<ResponseDTO> findOperations() {
        return ResponseUtils.get(operationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/operations/{id}")
    public ResponseEntity<ResponseDTO> findOperationById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(operationService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/operations")
    public ResponseEntity<ResponseDTO> createOperation(@RequestBody @Valid OperationDTO operationDTO) {
        return ResponseUtils.get(operationService.save(operationDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/operations/{id}")
    public ResponseEntity<ResponseDTO> deleteOperationById(@PathVariable("id") UUID id) {
        operationService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_OPERATION_SUCCESS, HttpStatus.OK);
    }
}
