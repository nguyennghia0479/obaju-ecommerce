package cybersoft.javabackend.java18.obajuecommerce.app_product.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_product.validation.validator.UniqueProductNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueProductNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueProductName {
    String message() default "{product.name.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
