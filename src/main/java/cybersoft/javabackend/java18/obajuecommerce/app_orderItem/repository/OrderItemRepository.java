package cybersoft.javabackend.java18.obajuecommerce.app_orderItem.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Query("select o from OrderItem o where o.order.id like ?1")
    List<OrderItem> findAllByOrderId(UUID id);
}
