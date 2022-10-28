package cybersoft.javabackend.java18.obajuecommerce.role.repository;

import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("select (count(r) > 0) from Role r where r.deleted = false and r.name = ?1")
    boolean isExistedByName(String name);

    @Query("select (count(r) > 0) from Role r where r.deleted = false and r.code = ?1")
    boolean isExistedByCode(String code);

    @Modifying
    @Query("update Role r set r.deleted = true where r.id = ?1")
    void removeById(UUID id);

    @Query("select r from Role r left join fetch r.operations")
    List<Role> findAllIncludeOperation();
}
