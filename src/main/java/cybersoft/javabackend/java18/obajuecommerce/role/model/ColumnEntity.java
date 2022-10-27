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

    @UtilityClass
    static class Operation {
        static final String TABLE_NAME = "OE_OPERATION";
        static final String NAME = "OE_O_NAME";
        static final String DESCRIPTION = "OE_O_DESCRIPTION";
        static final String TYPE = "OE_O_TYPE";
        static final String DELETE = "DELETED";
    }

    @UtilityClass
    static class RoleMappedOperation {
        static final String OPERATION_MAPPED_ROLE = "operations";
        static final String JOIN_TABLE = "OE_ROLE_OPERATION";
        static final String JOIN_TABLE_ROLE_ID = "OE_ROLE_ID";
        static final String JOIN_TABLE_OPERATION_ID = "OE_OPERATION_ID";
    }
}
