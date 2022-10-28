package cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.user.validation.validator.UniqueUserGroupNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserGroupNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueUserGroupName {
    String message() default "{userGroup.name.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
