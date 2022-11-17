package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model;

import cybersoft.javabackend.java18.obajuecommerce.app_product.model.Product;
import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

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
@Table(name = ColumnEntity.Subcategory.TABLE_NAME)
@Where(clause = "deleted=false")
public class Subcategory extends BaseEntity {
    @Column(name = ColumnEntity.Subcategory.NAME, nullable = false, length = 20)
    private String name;

    @Column(name = ColumnEntity.Subcategory.NAME_URL, nullable = false, length = 10)
    private String nameURL;

    @Column(name = ColumnEntity.Subcategory.CODE, nullable = false, length = 20)
    private String code;

    @Column(name = ColumnEntity.Subcategory.DESCRIPTION, length = 1000)
    private String description;

    @Column(name = ColumnEntity.Subcategory.CATEGORY, nullable = false)
    private Category category;

    @Column(name = ColumnEntity.Subcategory.DELETED)
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = ColumnEntity.Subcategory.SUBCATEGORY_MAP)
    private Set<Product> products = new LinkedHashSet<>();

    public enum Category {
        SHIRT,
        PANTS,
        SHOES,
        ACCESSORY
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Subcategory subcategory = (Subcategory) obj;
        return Objects.equals(id, subcategory.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
