package cybersoft.javabackend.java18.obajuecommerce.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DateTimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    @Column(name = Columns.ID)
    protected UUID id;

    @Version
    @Column(name = Columns.VERSION)
    protected int version;

    @CreatedBy
    @Column(name = Columns.CREATED_BY)
    protected String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @CreatedDate
    @Column(name = Columns.CREATED_AT)
    protected LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = Columns.LAST_MODIFIED_BY)
    protected String lastModifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @LastModifiedDate
    @Column(name = Columns.LAST_MODIFIED_AT)
    protected LocalDateTime lastModifiedAt;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        BaseEntity baseEntity = (BaseEntity) obj;
        return Objects.equals(id, baseEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @UtilityClass
    static class Columns {
        static final String ID = "ID";
        static final String VERSION = "VERSION";
        static final String CREATED_BY = "CREATED_BY";
        static final String CREATED_AT = "CREATED_AT";
        static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
        static final String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";
    }
}
