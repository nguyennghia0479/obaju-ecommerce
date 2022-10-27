package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.role.dto.OperationDTO;

import java.util.List;
import java.util.UUID;

public interface OperationService {
    List<OperationDTO> findAll();

    OperationDTO findById(UUID id);

    OperationDTO save(OperationDTO operationDTO);

    void deleteById(UUID id);
}
