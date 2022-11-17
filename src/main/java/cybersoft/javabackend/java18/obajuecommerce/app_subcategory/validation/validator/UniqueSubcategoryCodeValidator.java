package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.repository.SubcategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.annotation.UniqueSubcategoryCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSubcategoryCodeValidator implements ConstraintValidator<UniqueSubcategoryCode, String> {
    private final SubcategoryRepository subcategoryRepository;
    private String message;

    public UniqueSubcategoryCodeValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public void initialize(UniqueSubcategoryCode uniqueSubcategoryCode) {
        message = uniqueSubcategoryCode.message();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if(subcategoryRepository.findByCode(code).isEmpty())
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
