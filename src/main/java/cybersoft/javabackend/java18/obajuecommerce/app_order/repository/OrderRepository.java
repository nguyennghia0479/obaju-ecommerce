package cybersoft.javabackend.java18.obajuecommerce.app_order.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("select o from Order o where o.user.username like ?1 order by o.lastModifiedAt desc")
    List<Order> findAllByUsername(String username);

    @Query("select count(o) from Order o")
    Integer countAllOrder();
}
