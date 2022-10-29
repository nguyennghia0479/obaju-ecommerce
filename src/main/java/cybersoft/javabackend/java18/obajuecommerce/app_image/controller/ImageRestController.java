package cybersoft.javabackend.java18.obajuecommerce.app_image.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.ImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.UploadImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.service.ImageService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.FileExceptionMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
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

    @PostMapping(value = "/upload-images", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseDTO> uploadImages(@ModelAttribute @Valid UploadImageDTO uploadImageDTO, BindingResult result) {
        if(result.hasErrors())
            return ResponseUtils.get(FileExceptionMessageUtils.IMAGE_NOT_FOUND, HttpStatus.BAD_REQUEST);
        List<ImageDTO> images = imageService.uploadImage(uploadImageDTO);
        if(!images.isEmpty()) {
            return ResponseUtils.get(images, HttpStatus.CREATED);
        }
        return ResponseUtils.get(FileExceptionMessageUtils.UPLOAD_IMAGE_ERROR, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<ResponseDTO> deleteFile(@PathVariable("id") UUID id) {
        imageService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_IMAGE_SUCCESS, HttpStatus.OK);
    }
}
