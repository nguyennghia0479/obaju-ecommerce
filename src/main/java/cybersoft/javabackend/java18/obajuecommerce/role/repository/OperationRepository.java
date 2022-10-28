package cybersoft.javabackend.java18.obajuecommerce.role.repository;

import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    @Query("select (count(o) > 0) from Operation o where o.deleted = false and o.name = ?1")
    boolean isExistedByName(String name);

    @Modifying
    @Query("update Operation o set o.deleted = true where o.id = ?1")
    void removeById(UUID id);
}
