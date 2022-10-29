package cybersoft.javabackend.java18.obajuecommerce.common.exception;

import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerMaxUploadSizeExceededException() {
        return ResponseUtils.error(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerConstraintViolationException(ConstraintViolationException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerUserNotFoundException(UserNotFoundException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerPermissionException(PermissionException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerFileException(FileException exception) {
        return ResponseUtils.error(exception, HttpStatus.BAD_REQUEST);
    }
}
