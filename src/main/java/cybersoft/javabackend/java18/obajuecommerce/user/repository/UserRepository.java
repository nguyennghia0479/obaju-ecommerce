package cybersoft.javabackend.java18.obajuecommerce.user.repository;

import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select (count(u) > 0) from User u where u.deleted = false and u.username = ?1")
    boolean isExistedByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.deleted = false and u.email = ?1")
    boolean isExistedByEmail(String email);

    @Query("select (count(u) > 0) from User u where u.deleted = false and u.phoneNum = ?1")
    boolean isExistedByPhoneNum(String phoneNum);

    @Modifying
    @Query("update User u set u.deleted = true where u.id = ?1")
    void removeById(UUID id);

    @Query("select u from User u where u.status = 'ACTIVE' and u.username = ?1")
    Optional<User> findByUsername(String username);
}
