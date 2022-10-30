package cybersoft.javabackend.java18.obajuecommerce.app_product.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("select p from Product p left join fetch p.images where p.name = ?1")
    Optional<Product> getByName(String name);

    @Modifying
    @Query("update Product p set p.deleted = true where p.id = ?1")
    void removeById(UUID id);

    @Query("""
            select count(p)
            from Product p, Subcategory sc
            where p.subcategory.id = sc.id
            and sc.code = ?1
            """)
    int countProductBySubcategory(String subcategoryCode);
}
