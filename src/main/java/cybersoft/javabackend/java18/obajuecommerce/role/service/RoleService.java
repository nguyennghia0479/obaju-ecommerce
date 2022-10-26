package cybersoft.javabackend.java18.obajuecommerce.role.service;

import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.role.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();

    RoleDTO findByCode(String code);

    RoleCreateDTO save(RoleCreateDTO roleCreateDTO);

    void deleteByCode(String code);
}
