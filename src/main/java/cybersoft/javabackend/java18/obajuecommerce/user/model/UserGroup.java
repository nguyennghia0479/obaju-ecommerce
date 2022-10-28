package cybersoft.javabackend.java18.obajuecommerce.user.model;

import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = ColumnEntity.UserGroup.TABLE_NAME)
@Where(clause = "deleted=false")
public class UserGroup extends BaseEntity {
    @Column(name = ColumnEntity.UserGroup.NAME, nullable = false, length = 20)
    private String name;

    @Column(name = ColumnEntity.UserGroup.DESCRIPTION)
    private String description;

    @Column(name = ColumnEntity.UserGroup.DELETE)
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = ColumnEntity.UserGroupMappedUser.JOIN_TABLE,
            joinColumns = @JoinColumn(name = ColumnEntity.UserGroupMappedUser.JOIN_TABLE_USER_GROUP_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnEntity.UserGroupMappedUser.JOIN_TABLE_USER_ID)
    )
    private Set<User> users = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = ColumnEntity.UserGroupMappedRole.JOIN_TABLE,
            joinColumns = @JoinColumn(name = ColumnEntity.UserGroupMappedRole.JOIN_TABLE_USER_GROUP_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnEntity.UserGroupMappedRole.JOIN_TABLE_ROLE_ID)
    )
    private Set<Role> roles = new LinkedHashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getUserGroups().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getUserGroups().remove(this);
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUserGroups().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUserGroups().remove(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserGroup userGroup = (UserGroup) obj;
        return Objects.equals(id, userGroup.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
