package cybersoft.javabackend.java18.obajuecommerce.app_stock.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    @Query("select s from Stock s where s.product.id = ?1 and s.productSize.id = ?2")
    Optional<Stock> findByProductIdAndProductSizeId(UUID productId, UUID productSizeId);
}
