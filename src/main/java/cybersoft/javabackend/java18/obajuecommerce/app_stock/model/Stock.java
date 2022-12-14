package cybersoft.javabackend.java18.obajuecommerce.app_stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.model.ProductSize;
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
@Table(name = ColumnEntity.Stock.TABLE_NAME)
public class Stock extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.Stock.PRODUCT_ID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.Stock.PRODUCT_SIZE_ID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProductSize productSize;

    @Column(name = ColumnEntity.Stock.QUANTITY, nullable = false)
    private int quantity;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Stock stock = (Stock) obj;
        return Objects.equals(id, stock.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
