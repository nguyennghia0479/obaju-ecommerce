package cybersoft.javabackend.java18.obajuecommerce.app_product.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_product.validation.annotation.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {
    private String message;

    @Override
    public void initialize(Image imageNotNull) {
        message = imageNotNull.message();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file != null && isSupportedContentType(Objects.requireNonNull(file.getContentType())))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
}
