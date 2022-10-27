package cybersoft.javabackend.java18.obajuecommerce.user.model;

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
@Table(name = ColumnEntity.User.TABLE_NAME)
@Where(clause = "deleted=false")
public class User extends BaseEntity {
    @Column(name = ColumnEntity.User.USERNAME, unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = ColumnEntity.User.PASSWORD, nullable = false, length = 100)
    private String password;

    @Column(name = ColumnEntity.User.EMAIL, unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = ColumnEntity.User.FULL_NAME, nullable = false, length = 100)
    private String fullName;

    @Column(name = ColumnEntity.User.PHONE_NUM, unique = true, nullable = false, length = 10)
    private String phoneNum;

    @Column(name = ColumnEntity.User.STATUS, length = 20)
    private Status status = Status.ACTIVE;

    @Column(name = ColumnEntity.User.DELETE)
    private boolean deleted = Boolean.FALSE;

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return Objects.equals(id, user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
