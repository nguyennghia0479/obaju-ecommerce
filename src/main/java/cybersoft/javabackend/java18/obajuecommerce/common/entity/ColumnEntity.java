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
    public static class Subcategory {
        public static final String TABLE_NAME = "OE_SUBCATEGORY";
        public static final String NAME = "OE_SC_NAME";
        public static final String NAME_URL = "OE_SC_NAME_URL";
        public static final String CODE = "OE_SC_CODE";
        public static final String DESCRIPTION = "OE_SC_DESCRIPTION";
        public static final String CATEGORY = "OE_SC_CATEGORY";
        public static final String DELETED = "DELETED";
        public static final String SUBCATEGORY_MAP = "subcategory";
    }

    @UtilityClass
    public static class Image {
        public static final String TABLE_NAME = "OE_IMAGE";
        public static final String NAME = "OE_I_NAME";
        public static final String IMAGE_URL = "OE_I_IMAGE_URL";
        public static final String PRODUCT_ID = "OE_I_PRODUCT_ID";
    }

    @UtilityClass
    public static class Product {
        public static final String TABLE_NAME = "OE_PRODUCT";
        public static final String NAME = "OE_P_NAME";
        public static final String NAME_URL = "OE_P_NAME_URL";
        public static final String CODE = "OE_P_CODE";
        public static final String AVATAR_URL = "OE_P_AVATAR_URL";
        public static final String PRICE = "OE_P_PRICE";
        public static final String COLOR = "OE_P_COLOR";
        public static final String DELETED = "DELETED";
        public static final String SUBCATEGORY_ID = "OE_P_SUBCATEGORY_ID";
        public static final String PRODUCT_MAP = "product";
    }

    @UtilityClass
    public static class ProductSize {
        public static final String TABLE_NAME = "OE_PRODUCT_SIZE";
        public static final String SIZE = "OE_PS_SIZE";
        public static final String PRODUCT_SIZE_MAP = "productSize";
    }

    @UtilityClass
    public static class Stock {
        public static final String TABLE_NAME = "OE_STOCK";
        public static final String PRODUCT_ID = "OE_S_PRODUCT_ID";
        public static final String PRODUCT_SIZE_ID = "OE_S_PRODUCT_SIZE_ID";
        public static final String QUANTITY = "OE_S_QUANTITY";
        public static final String STOCK_MAP = "stock";
    }

    @UtilityClass
    public static class Order {
        public static final String TABLE_NAME = "OE_ORDER";
        public static final String TOTAL_PRICE = "OE_O_TOTAL_PRICE";
        public static final String ADDRESS = "OE_O_ADDRESS";
        public static final String PAYMENT = "OE_O_PAYMENT";
        public static final String USER_ID = "OE_O_USER_ID";
        public static final String ORDER_MAP = "order";
    }

    @UtilityClass
    public static class OrderItem {
        public static final String TABLE_NAME = "OE_ORDER_ITEM";
        public static final String QUANTITY = "OE_OI_QUANTITY";
        public static final String PRICE = "OE_OI_PRICE";
        public static final String ORDER_ID = "OE_OI_ORDER_ID";
        public static final String STOCK_ID = "OE_OI_STOCK_ID";

    }
}
