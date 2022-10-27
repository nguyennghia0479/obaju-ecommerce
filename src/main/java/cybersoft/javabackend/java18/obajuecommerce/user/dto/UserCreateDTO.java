package cybersoft.javabackend.java18.obajuecommerce.user.dto;

import cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation.UniqueEmail;
import cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation.UniquePhoneNum;
import cybersoft.javabackend.java18.obajuecommerce.user.validation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO implements Serializable {
    private UUID id;

    @Size(min = 5, max = 100, message = "{user.username.size}")
    @NotBlank(message = "{user.username.blank}")
    @UniqueUsername
    private String username;

    @Size(min = 5, max = 100, message = "{user.password.size}")
    @NotBlank(message = "{user.password.blank}")
    private String password;

    @Size(min = 5, max = 100, message = "{user.email.size}")
    @NotBlank(message = "{user.email.blank}")
    @UniqueEmail
    @Email(message = "{user.email.valid}")
    private String email;

    @Size(min = 5, max = 100, message = "{user.fullName.size}")
    @NotBlank(message = "{user.fullName.blank}")
    private String fullName;

    @Size(min = 10, max = 10, message = "{user.phoneNum.size}")
    @NotBlank(message = "{user.phoneNum.blank}")
    @UniquePhoneNum
    private String phoneNum;
}
