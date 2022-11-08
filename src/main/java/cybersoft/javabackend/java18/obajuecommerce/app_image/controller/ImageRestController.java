package cybersoft.javabackend.java18.obajuecommerce.app_image.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.ImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.UploadImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.service.ImageService;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.FileException;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.FileExceptionMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ImageRestController {
    private final ImageService imageService;

    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/images")
    public ResponseEntity<ResponseDTO> findImages() {
        return ResponseUtils.get(imageService.findAll(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value = "/admin/images", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseDTO> uploadImages(@ModelAttribute @Valid UploadImageDTO uploadImageDTO, BindingResult result) {
        if(result.hasErrors())
            return ResponseUtils.error(result, HttpStatus.BAD_REQUEST);
        List<ImageDTO> images = imageService.uploadImage(uploadImageDTO);
        if(!images.isEmpty()) {
            return ResponseUtils.get(images, HttpStatus.CREATED);
        }
        throw new FileException(FileExceptionMessageUtils.UPLOAD_IMAGE_ERROR);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/admin/images/{id}")
    public ResponseEntity<ResponseDTO> deleteFile(@PathVariable("id") UUID id) {
        imageService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_IMAGE_SUCCESS, HttpStatus.OK);
    }
}
