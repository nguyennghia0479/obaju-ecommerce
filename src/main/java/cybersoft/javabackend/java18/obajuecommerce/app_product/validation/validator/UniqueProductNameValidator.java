package cybersoft.javabackend.java18.obajuecommerce.app_product.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_product.repository.ProductRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_product.validation.annotation.UniqueProductName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {
    private final ProductRepository productRepository;
    private String message;

    public UniqueProductNameValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void initialize(UniqueProductName uniqueProductName) {
        message = uniqueProductName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(productRepository.getByName(name).isEmpty())
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
