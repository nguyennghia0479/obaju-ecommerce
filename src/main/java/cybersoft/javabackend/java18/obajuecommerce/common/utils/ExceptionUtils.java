package cybersoft.javabackend.java18.obajuecommerce.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@UtilityClass
public class ExceptionUtils {
    public List<String> getErrors(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();
    }

    public List<String> getErrors(MethodArgumentNotValidException exception) {
        return exception.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    public List<String> getErrors(RuntimeException exception) {
        return List.of(exception.getMessage());
    }
}
