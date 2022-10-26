package cybersoft.javabackend.java18.obajuecommerce.role.model;

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
@Table(name = ColumnEntity.Role.TABLE_NAME)
@Where(clause = "deleted=false")
public class Role extends BaseEntity {
    @Column(name = ColumnEntity.Role.NAME, unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = ColumnEntity.Role.CODE, unique = true, nullable = false, length = 20)
    private String code;

    @Column(name = ColumnEntity.Role.DESCRIPTION)
    private String description;

    @Column(name = ColumnEntity.Role.DELETE)
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Role role = (Role) obj;
        return Objects.equals(id, role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
