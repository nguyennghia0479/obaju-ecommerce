package cybersoft.javabackend.java18.obajuecommerce.role.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ColumnEntity {
    @UtilityClass
    static class Role {
        static final String TABLE_NAME = "OE_ROLE";
        static final String NAME = "OE_R_NAME";
        static final String CODE = "OE_R_CODE";
        static final String DESCRIPTION = "OE_R_DESCRIPTION";
        static final String DELETE = "DELETED";
    }
}
