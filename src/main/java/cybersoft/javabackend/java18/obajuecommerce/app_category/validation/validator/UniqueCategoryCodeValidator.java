package cybersoft.javabackend.java18.obajuecommerce.app_category.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_category.repository.CategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_category.validation.annotation.UniqueCategoryCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryCodeValidator implements ConstraintValidator<UniqueCategoryCode, String> {
    private final CategoryRepository categoryRepository;
    private String message;

    public UniqueCategoryCodeValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(UniqueCategoryCode uniqueCategoryCode) {
        message = uniqueCategoryCode.message();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if(categoryRepository.findByCode(code).isEmpty())
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
