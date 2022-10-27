package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleDTO> findAll();

    RoleDTO findById(UUID id);

    RoleDTO save(RoleDTO roleDTO);

    void deleteById(UUID id);
}
