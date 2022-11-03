package cybersoft.javabackend.java18.obajuecommerce.app_product_size.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_product_size.repository.ProductSizeRepository;
import cybersoft.javabackend.java18.obajuecommerce.app_product_size.validation.annotation.UniqueProductSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProductSizeValidator implements ConstraintValidator<UniqueProductSize, String> {
    private final ProductSizeRepository productSizeRepository;
    private String message;

    public UniqueProductSizeValidator(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    public void initialize(UniqueProductSize uniqueProductSize) {
        message = uniqueProductSize.message();
    }

    @Override
    public boolean isValid(String size, ConstraintValidatorContext context) {
        if(!productSizeRepository.isExistedBySize(size))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
