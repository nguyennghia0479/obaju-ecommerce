package cybersoft.javabackend.java18.obajuecommerce.app_order.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
