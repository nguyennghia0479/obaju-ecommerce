package cybersoft.javabackend.java18.obajuecommerce.common.image;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class FileRestController {
    private final FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/download/{fileName}")
    public Object downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = fileService.loadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "fileName =\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
