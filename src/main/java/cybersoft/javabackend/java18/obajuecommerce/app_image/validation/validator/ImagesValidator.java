package cybersoft.javabackend.java18.obajuecommerce.app_image.validation.validator;

import cybersoft.javabackend.java18.obajuecommerce.app_image.validation.annotation.Images;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class ImagesValidator implements ConstraintValidator<Images, MultipartFile[]> {
    private String message;

    @Override
    public void initialize(Images images) {
        message = images.message();
    }

    @Override
    public boolean isValid(MultipartFile[] files, ConstraintValidatorContext context) {
        if(files != null) {
            boolean result = Arrays.stream(files).anyMatch(file ->
                    file == null || !isSupportedContentType(Objects.requireNonNull(file.getContentType())));
            if(!result)
                return true;
        }
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
