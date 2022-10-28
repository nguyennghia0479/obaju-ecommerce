package cybersoft.javabackend.java18.obajuecommerce.user.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.user.repository.UserGroupRepository;
import cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation.UniqueUserGroupName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserGroupNameValidator implements ConstraintValidator<UniqueUserGroupName, String> {
    private final UserGroupRepository userGroupRepository;
    private String message;

    public UniqueUserGroupNameValidator(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public void initialize(UniqueUserGroupName uniqueUserGroupName) {
        message = uniqueUserGroupName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(!userGroupRepository.isExistedByName(name))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
