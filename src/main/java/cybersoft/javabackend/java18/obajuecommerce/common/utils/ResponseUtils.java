package cybersoft.javabackend.java18.obajuecommerce.common.utils;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class ResponseUtils {
    public ResponseEntity<ResponseDTO> get(Object result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(result)
                        .hasError(false)
                        .errors(Collections.emptyList())
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(List.of("One or more files are too large!"))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(ConstraintViolationException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(false)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(MethodArgumentNotValidException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(false)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(ResourceNotFoundException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(false)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }
}
