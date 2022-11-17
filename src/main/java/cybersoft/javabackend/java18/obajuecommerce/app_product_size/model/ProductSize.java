package cybersoft.javabackend.java18.obajuecommerce.app_product_size.model;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = ColumnEntity.ProductSize.TABLE_NAME)
public class ProductSize extends BaseEntity {
    @Column(name = ColumnEntity.ProductSize.SIZE, nullable = false, length = 10)
    private String size;

    @OneToMany(mappedBy = ColumnEntity.ProductSize.PRODUCT_SIZE_MAP)
    private Set<Stock> stocks = new LinkedHashSet<>();

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        ProductSize productSize = (ProductSize) obj;
        return Objects.equals(id, productSize.getId());
    }
}
