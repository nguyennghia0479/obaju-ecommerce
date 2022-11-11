package cybersoft.javabackend.java18.obajuecommerce.app_category.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("select c from Category c left join fetch c.subcategories")
    List<Category> findAllIncludeSubcategories();

    Optional<Category> findByName(String name);

    Optional<Category> findByCode(String code);

    @Modifying
    @Query("update Category c set c.deleted = true where c.id = ?1")
    void removeById(UUID id);
}
