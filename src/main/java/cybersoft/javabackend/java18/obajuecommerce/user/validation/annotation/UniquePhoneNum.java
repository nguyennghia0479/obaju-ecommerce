package cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation;

import cybersoft.javabackend.java18.obajuecommerce.user.validation.validator.UniquePhoneNumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneNumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniquePhoneNum {
    String message() default "{user.phoneNum.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
