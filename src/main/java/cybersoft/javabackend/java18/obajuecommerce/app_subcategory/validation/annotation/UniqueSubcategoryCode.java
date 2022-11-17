package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.validator.UniqueSubcategoryCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueSubcategoryCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueSubcategoryCode {
    String message() default "{subcategory.code.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
