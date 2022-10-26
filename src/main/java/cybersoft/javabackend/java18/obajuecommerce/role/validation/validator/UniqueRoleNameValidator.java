package cybersoft.javabackend.java18.obajuecommerce.role.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import cybersoft.javabackend.java18.obajuecommerce.role.validation.annotation.UniqueRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {
    private final RoleRepository roleRepository;
    private String message;

    public UniqueRoleNameValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleName uniqueRoleName) {
        message = uniqueRoleName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(!roleRepository.isExistedByName(name))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
