package cybersoft.javabackend.java18.obajuecommerce.app_product_size.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, UUID> {
    @Query("select (count(ps) > 0) from ProductSize ps where ps.size = ?1")
    boolean isExistedBySize(String size);

    @Query("""
            select ps
            from ProductSize ps, Stock s
            where ps.id = s.productSize.id
            and s.product.id = ?1
            """)
    List<ProductSize> findByProductId(UUID id);
}
