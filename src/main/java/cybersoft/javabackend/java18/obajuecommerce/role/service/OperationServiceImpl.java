package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.OperationDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.mapper.OperationMapper;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.OperationRepository;
import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final RoleRepository roleRepository;

    public OperationServiceImpl(OperationRepository operationRepository, RoleRepository roleRepository) {
        this.operationRepository = operationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<OperationDTO> findAll() {
        return operationRepository.findAll()
                .stream()
                .map(OperationMapper.INSTANCE::operationToOperationDTO)
                .toList();
    }

    @Override
    public OperationDTO findById(UUID id) {
        return operationRepository.findById(id)
                .map(OperationMapper.INSTANCE::operationToOperationDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.OPERATION_ID_NOT_FOUND));
    }

    @Override
    public OperationDTO save(OperationDTO operationDTO) {
        Operation operationCreate = OperationMapper.INSTANCE.operationDTOToOperation(operationDTO);
        operationRepository.save(operationCreate);
        return OperationMapper.INSTANCE.operationToOperationDTO(operationCreate);
    }

    @Override
    public void deleteById(UUID id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.OPERATION_ID_NOT_FOUND));
        List<Role> roles = roleRepository.findAllIncludeOperation();
        roles.forEach(r -> r.removeOperation(operation));
        operationRepository.removeById(operation.getId());
    }
}
