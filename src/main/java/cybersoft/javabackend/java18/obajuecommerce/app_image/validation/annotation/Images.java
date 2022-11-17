package cybersoft.javabackend.java18.obajuecommerce.app_image.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.app_image.validation.validator.ImagesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImagesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Images {
    String message() default "{image.file.valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
