package cybersoft.javabackend.java18.obajuecommerce.app_image.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadImageDTO implements Serializable {
    private transient MultipartFile[] files;

    private UUID productId;
}
