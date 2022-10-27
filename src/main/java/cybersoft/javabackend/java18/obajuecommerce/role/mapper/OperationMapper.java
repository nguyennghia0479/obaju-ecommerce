package cybersoft.javabackend.java18.obajuecommerce.role.mapper;

import cybersoft.javabackend.java18.obajuecommerce.role.dto.OperationDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationDTO operationToOperationDTO(Operation operation);

    Operation operationDTOToOperation(OperationDTO operationDTO);
}
