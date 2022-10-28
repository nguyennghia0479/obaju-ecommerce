package cybersoft.javabackend.java18.obajuecommerce.app_category.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_category.validation.validator.UniqueCategoryCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCategoryCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueCategoryCode {
    String message() default "{category.code.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
