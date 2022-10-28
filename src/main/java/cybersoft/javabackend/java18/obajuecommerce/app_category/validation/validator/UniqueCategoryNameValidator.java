package cybersoft.javabackend.java18.obajuecommerce.app_category.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_category.repository.CategoryRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_category.validation.annotation.UniqueCategoryName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {
    private final CategoryRepository categoryRepository;
    private String message;

    public UniqueCategoryNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(UniqueCategoryName uniqueCategoryName) {
        message = uniqueCategoryName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(categoryRepository.findByName(name).isEmpty())
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
