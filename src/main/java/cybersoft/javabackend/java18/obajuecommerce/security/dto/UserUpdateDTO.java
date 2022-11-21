package cybersoft.javabackend.java18.obajuecommerce.security.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO implements Serializable {
    private String username;

    @Size(min = 5, max = 100, message = "{user.email.size}")
    @NotBlank(message = "{user.email.blank}")
    @Email(message = "{user.email.valid}")
    private String email;

    @Size(min = 5, max = 100, message = "{user.fullName.size}")
    @NotBlank(message = "{user.fullName.blank}")
    private String fullName;

    @Size(min = 10, max = 10, message = "{user.phoneNum.size}")
    @NotBlank(message = "{user.phoneNum.blank}")
    private String phoneNum;
}
