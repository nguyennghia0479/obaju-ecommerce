package cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.repository.SubcategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_subcategory.validation.annotation.UniqueSubcategoryName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSubcategoryNameValidator implements ConstraintValidator<UniqueSubcategoryName, String> {
    private final SubcategoryRepository subcategoryRepository;
    private String message;

    public UniqueSubcategoryNameValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public void initialize(UniqueSubcategoryName uniqueSubcategoryName) {
        message = uniqueSubcategoryName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(subcategoryRepository.findByName(name).isEmpty())
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
