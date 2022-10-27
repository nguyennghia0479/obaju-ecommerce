package cybersoft.javabackend.java18.obajuecommerce.user.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation.UniquePhoneNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumValidator implements ConstraintValidator<UniquePhoneNum, String> {
    private final UserRepository userRepository;
    private String message;

    public UniquePhoneNumValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniquePhoneNum uniquePhoneNum) {
        message = uniquePhoneNum.message();
    }

    @Override
    public boolean isValid(String phoneNum, ConstraintValidatorContext context) {
        if(!userRepository.isExistedByPhoneNum(phoneNum))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
