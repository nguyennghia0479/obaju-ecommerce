package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, UUID> {
    Optional<Subcategory> findByName(String name);

    Optional<Subcategory> findByCode(String code);

    @Modifying
    @Query("update Subcategory sc set sc.deleted = true where sc.id = ?1")
    void removeById(UUID id);

    @Query("select distinct sc from Subcategory sc left join fetch sc.products")
    List<Subcategory> findAllIncludeProducts();
}
