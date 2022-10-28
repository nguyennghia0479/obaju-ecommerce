package cybersoft.javabackend.java18.obajuecommerce.app_category.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_category.validation.validator.UniqueCategoryNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCategoryNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueCategoryName {
    String message() default "{category.name.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
