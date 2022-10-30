package cybersoft.javabackend.java18.obajuecommerce.app_product.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_product.validation.validator.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Image {
    String message() default "{product.avatar.valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
