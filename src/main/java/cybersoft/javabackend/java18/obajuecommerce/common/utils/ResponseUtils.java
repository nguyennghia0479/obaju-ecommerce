package cybersoft.javabackend.java18.obajuecommerce.common.utils;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.*;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
                        .hasError(true)
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
                        .hasError(true)
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
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(UserNotFoundException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(PermissionException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(FileException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(BindingResult result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(result.getAllErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .toList())
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(DeleteException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(DuplicateException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }

    public ResponseEntity<ResponseDTO> error(QuantityException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .errors(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .statusCode(status.value())
                        .build(),
                status);
    }
}
