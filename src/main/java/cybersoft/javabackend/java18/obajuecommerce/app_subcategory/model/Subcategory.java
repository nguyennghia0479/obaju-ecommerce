package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.obajuecommerce.app_category.model.Category;
import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = ColumnEntity.Subcategory.CODE, nullable = false, length = 20)
    private String code;

    @Column(name = ColumnEntity.Subcategory.DESCRIPTION)
    private String description;

    @Column(name = ColumnEntity.Subcategory.DELETED)
    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.Subcategory.CATEGORY_ID, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

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
