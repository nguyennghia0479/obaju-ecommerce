package cybersoft.javabackend.java18.obajuecommerce.app_image.model;

import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
@Table(name = ColumnEntity.Image.TABLE_NAME)
public class Image extends BaseEntity {
    @Column(name = ColumnEntity.Image.NAME, nullable = false)
    private String name;

    @Column(name = ColumnEntity.Image.IMAGE_URL, nullable = false)
    private String imageURL;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Image image = (Image) obj;
        return Objects.equals(id, image.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
