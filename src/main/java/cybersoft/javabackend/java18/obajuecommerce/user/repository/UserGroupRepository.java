package cybersoft.javabackend.java18.obajuecommerce.user.repository;

import cybersoft.javabackend.java18.obajuecommerce.user.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
    @Query("select (count(ug) > 0) from UserGroup ug where ug.deleted = false and ug.name = ?1")
    boolean isExistedByName(String name);

    @Modifying
    @Query("update UserGroup ug set ug.deleted = true where ug.id = ?1")
    void removeById(UUID id);

    @Query("select ug from UserGroup ug left join fetch ug.users")
    List<UserGroup> findAllIncludeUser();

    @Query("select ug from UserGroup ug left join fetch ug.roles")
    List<UserGroup> findAllIncludeRole();

    Optional<UserGroup> findByName(String name);
}
