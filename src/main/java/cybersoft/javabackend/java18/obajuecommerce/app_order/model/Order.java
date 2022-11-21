package cybersoft.javabackend.java18.obajuecommerce.app_order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.obajuecommerce.app_orderItem.model.OrderItem;
import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = ColumnEntity.Order.TABLE_NAME)
public class Order extends BaseEntity {
    @Column(name = ColumnEntity.Order.CODE, nullable = false)
    private String code;

    @Column(name = ColumnEntity.Order.TOTAL_PRICE, nullable = false)
    private Double totalPrice;

    @Column(name = ColumnEntity.Order.ADDRESS, nullable = false)
    private String address;

    @Column(name = ColumnEntity.Order.PAYMENT, nullable = false)
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Column(name = ColumnEntity.Order.STATUS_ORDER, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @OneToMany(mappedBy = ColumnEntity.Order.ORDER_MAP)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.Order.USER_ID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public enum Payment {
        CASH,
        VISA,
        MASTER_CARD,
        PAYPAL,
        ONLINE_BANKING
    }

    public enum StatusOrder {
        CANCELLED,
        PREPARED,
        RECEIVED
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Order order = (Order) obj;
        return Objects.equals(id, order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
