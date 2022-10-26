package cybersoft.javabackend.java18.obajuecommerce.common.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseDTO {
    private Object content;
    private boolean hasError;
    private List<String> errors;
    private String timestamp;
    private int statusCode;
}
