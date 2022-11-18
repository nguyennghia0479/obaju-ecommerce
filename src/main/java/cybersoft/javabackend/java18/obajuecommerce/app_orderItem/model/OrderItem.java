package cybersoft.javabackend.java18.obajuecommerce.app_orderItem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.obajuecommerce.app_order.model.Order;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = ColumnEntity.OrderItem.TABLE_NAME)
public class OrderItem extends BaseEntity {
    @Column(name = ColumnEntity.OrderItem.QUANTITY, nullable = false)
    private int quantity;

    @Column(name = ColumnEntity.OrderItem.PRICE, nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.OrderItem.ORDER_ID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.OrderItem.STOCK_ID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Stock stock;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        OrderItem orderItem = (OrderItem) obj;
        return Objects.equals(id, orderItem.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
