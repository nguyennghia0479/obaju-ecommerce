package cybersoft.javabackend.java18.obajuecommerce.common.entity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ColumnEntity {
    @UtilityClass
    public static class Role {
        public static final String TABLE_NAME = "OE_ROLE";
        public static final String NAME = "OE_R_NAME";
        public static final String CODE = "OE_R_CODE";
        public static final String DESCRIPTION = "OE_R_DESCRIPTION";
        public static final String DELETE = "DELETED";
    }

    @UtilityClass
    public static class Operation {
        public static final String TABLE_NAME = "OE_OPERATION";
        public static final String NAME = "OE_O_NAME";
        public static final String DESCRIPTION = "OE_O_DESCRIPTION";
        public static final String TYPE = "OE_O_TYPE";
        public static final String DELETE = "DELETED";
    }

    @UtilityClass
    public static class RoleMappedOperation {
        public static final String OPERATION_MAPPED_ROLE = "operations";
        public static final String JOIN_TABLE = "OE_ROLE_OPERATION";
        public static final String JOIN_TABLE_ROLE_ID = "OE_ROLE_ID";
        public static final String JOIN_TABLE_OPERATION_ID = "OE_OPERATION_ID";
    }

    @UtilityClass
    public static class User {
        public static final String TABLE_NAME = "OE_USER";
        public static final String USERNAME = "OE_U_USERNAME";
        public static final String PASSWORD = "OE_U_PASSWORD";
        public static final String EMAIL = "OE_U_EMAIL";
        public static final String FULL_NAME = "OE_U_FULL_NAME";
        public static final String PHONE_NUM = "OE_U_PHONE_NUM";
        public static final String STATUS = "OE_U_STATUS";
        public static final String DELETE = "DELETED";
    }
}
