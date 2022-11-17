package cybersoft.javabackend.java18.obajuecommerce.app_product_size.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.validation.validator.UniqueProductSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueProductSizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueProductSize {
    String message() default "{productSize.size.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
