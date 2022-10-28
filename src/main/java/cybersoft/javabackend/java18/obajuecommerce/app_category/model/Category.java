package cybersoft.javabackend.java18.obajuecommerce.app_category.model;

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
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = ColumnEntity.Category.TABLE_NAME)
@Where(clause = "deleted=false")
public class Category extends BaseEntity {
    @Column(name = ColumnEntity.Category.NAME, nullable = false, length = 20)
    private String name;

    @Column(name = ColumnEntity.Category.CODE, nullable = false, length = 20)
    private String code;

    @Column(name = ColumnEntity.Category.DELETED)
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Category category = (Category) obj;
        return Objects.equals(id, category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
