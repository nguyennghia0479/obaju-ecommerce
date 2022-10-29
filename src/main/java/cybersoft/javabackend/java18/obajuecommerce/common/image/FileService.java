package cybersoft.javabackend.java18.obajuecommerce.common.image;

import cybersoft.javabackend.java18.obajuecommerce.common.exception.FileException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.FileExceptionMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.config.FileConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class FileService {
    private final Path root;

    public FileService(FileConfig fileConfig) {
        this.root = Paths.get(fileConfig.getUploadDir());
        try {
            if(!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            throw new FileException(FileExceptionMessageUtils.ERROR_CREATE_ROOT_FOLDER + " " + e.getMessage());
        }
    }

    public Resource loadImage(String imgName) {
        try {
            Path file = root.resolve(imgName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException(ResourceNotFoundMessageUtils.LOAD_IMAGE_GET_ERROR);
            }
        } catch (MalformedURLException e) {
            throw new FileException(FileExceptionMessageUtils.ERROR_LOAD_IMG_FROM_ROOT + " " + e.getMessage());
        }
    }
}
