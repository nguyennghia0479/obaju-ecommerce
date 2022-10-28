package cybersoft.javabackend.java18.obajuecommerce.role.model;

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
import javax.persistence.ManyToMany;
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
@Table(name = ColumnEntity.Operation.TABLE_NAME)
@Where(clause = "deleted=false")
public class Operation extends BaseEntity {
    @Column(name = ColumnEntity.Operation.NAME, nullable = false, length = 20)
    private String name;

    @Column(name = ColumnEntity.Operation.DESCRIPTION)
    private String description;

    @Column(name = ColumnEntity.Operation.TYPE)
    private Type type;

    @Column(name = ColumnEntity.Operation.DELETE)
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = ColumnEntity.RoleMappedOperation.OPERATION_MAPPED_ROLE)
    private Set<Role> roles = new LinkedHashSet<>();

    public enum Type {
        FETCH,
        SAVE_OR_UPDATE,
        REMOVE
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Operation operation = (Operation) obj;
        return Objects.equals(id, operation.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
