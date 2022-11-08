package cybersoft.javabackend.java18.obajuecommerce.app_image.service;

import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.ImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.ImageIncludeProductDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.UploadImageDTO;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    List<ImageIncludeProductDTO> findAll();

    List<ImageDTO> uploadImage(UploadImageDTO uploadImageDTO);

    void deleteById(UUID id);
}
