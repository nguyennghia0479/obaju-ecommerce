package cybersoft.javabackend.java18.obajuecommerce.app_product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.obajuecommerce.app_image.model.Image;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.model.Subcategory;
import cybersoft.javabackend.java18.obajuecommerce.common.entity.ColumnEntity;
import cybersoft.javabackend.java18.obajuecommerce.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(name = ColumnEntity.Product.TABLE_NAME)
@Where(clause = "deleted=false")
public class Product extends BaseEntity {
    @Column(name = ColumnEntity.Product.NAME, nullable = false, length = 100)
    private String name;

    @Column(name = ColumnEntity.Product.NAME_URL, nullable = false, length = 100)
    private String nameURL;

    @Column(name = ColumnEntity.Product.CODE, nullable = false, length = 30)
    private String code;

    @Column(name = ColumnEntity.Product.AVATAR_URL)
    private String avatarURL;

    @Column(name = ColumnEntity.Product.PRICE, nullable = false)
    private Double price;

    @Column(name = ColumnEntity.Product.COLOR, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = ColumnEntity.Product.SUBCATEGORY_ID)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subcategory subcategory;

    @Column(name = ColumnEntity.Product.DELETED)
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = ColumnEntity.Product.PRODUCT_MAP)
    private Set<Image> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = ColumnEntity.Product.PRODUCT_MAP)
    private Set<Stock> stocks = new LinkedHashSet<>();

    public enum Color {
        WHITE,
        BLACK,
        RED,
        BLUE,
        DARK_BLUE,
        GREEN,
        LIGHT_BROWN,
        BROWN,
        LIGHT_GRAY,
        GRAY,
        DARK_GRAY,
        BEIGE,
        YELLOW
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Product product = (Product) obj;
        return Objects.equals(id, product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
