package cybersoft.javabackend.java18.obajuecommerce.role.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.role.repository.RoleRepository;
import cybersoft.javabackend.java18.obajuecommerce.role.validation.annotation.UniqueRoleCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleCodeValidator implements ConstraintValidator<UniqueRoleCode, String> {
    private final RoleRepository roleRepository;
    private String message;

    public UniqueRoleCodeValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleCode uniqueRoleCode) {
        message = uniqueRoleCode.message();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if(!roleRepository.isExistedByCode(code))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
