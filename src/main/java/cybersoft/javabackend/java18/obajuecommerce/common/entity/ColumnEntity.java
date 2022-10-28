package cybersoft.javabackend.java18.obajuecommerce.common.entity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ColumnEntity {
    @UtilityClass
    public static class UserGroupMappedUser {
        public static final String USER_MAPPED_USER_GROUP = "users";
        public static final String JOIN_TABLE = "OE_USER_GROUP_USER";
        public static final String JOIN_TABLE_USER_GROUP_ID = "OE_USER_GROUP_ID";
        public static final String JOIN_TABLE_USER_ID = "OE_USER_ID";
    }

    @UtilityClass
    public static class UserGroupMappedRole {
        public static final String ROLE_MAPPED_USER_GROUP = "roles";
        public static final String JOIN_TABLE = "OE_USER_GROUP_ROLE";
        public static final String JOIN_TABLE_USER_GROUP_ID = "OE_USER_GROUP_ID";
        public static final String JOIN_TABLE_ROLE_ID = "OE_ROLE_ID";
    }

    @UtilityClass
    public static class RoleMappedOperation {
        public static final String OPERATION_MAPPED_ROLE = "operations";
        public static final String JOIN_TABLE = "OE_ROLE_OPERATION";
        public static final String JOIN_TABLE_ROLE_ID = "OE_ROLE_ID";
        public static final String JOIN_TABLE_OPERATION_ID = "OE_OPERATION_ID";
    }

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

    @UtilityClass
    public static class UserGroup {
        public static final String TABLE_NAME = "OE_USER_GROUP";
        public static final String NAME = "OE_UG_NAME";
        public static final String DESCRIPTION = "OE_UG_DESCRIPTION";
        public static final String DELETE = "DELETED";
    }

    @UtilityClass
    public static class Category {
        public static final String TABLE_NAME = "OE_CATEGORY";
        public static final String NAME = "OE_C_NAME";
        public static final String CODE = "OE_C_CODE";
        public static final String DELETED = "DELETED";
        public static final String CATEGORY_MAP = "category";
    }

    @UtilityClass
    public static class Subcategory {
        public static final String TABLE_NAME = "OE_SUBCATEGORY";
        public static final String NAME = "OE_SC_NAME";
        public static final String CODE = "OE_SC_CODE";
        public static final String DESCRIPTION = "OE_SC_DESCRIPTION";
        public static final String DELETED = "DELETED";
        public static final String CATEGORY_ID = "OE_SC_CATEGORY_ID";
    }
}
