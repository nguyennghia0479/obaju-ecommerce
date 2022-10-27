package cybersoft.javabackend.java18.obajuecommerce.role.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.role.repository.OperationRepository;
import cybersoft.javabackend.java18.obajuecommerce.role.validation.annotation.UniqueOperationName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOperationNameValidator implements ConstraintValidator<UniqueOperationName, String> {
    private final OperationRepository operationRepository;
    private String message;

    public UniqueOperationNameValidator(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void initialize(UniqueOperationName uniqueOperationName) {
        message = uniqueOperationName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(!operationRepository.isExistedByName(name))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
