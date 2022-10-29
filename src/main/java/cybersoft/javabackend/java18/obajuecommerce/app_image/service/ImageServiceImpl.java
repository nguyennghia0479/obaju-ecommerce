package cybersoft.javabackend.java18.obajuecommerce.app_image.service;

import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.ImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.dto.UploadImageDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_image.mapper.ImageMapper;
import cybersoft.javabackend.java18.obajuecommerce.app_image.model.Image;
import cybersoft.javabackend.java18.obajuecommerce.app_image.repository.ImageRepository;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.FileException;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.ResourceNotFoundException;
import cybersoft.javabackend.java18.obajuecommerce.common.image.FileRestController;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.FileExceptionMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResourceNotFoundMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.config.FileConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final Path root;

    public ImageServiceImpl(ImageRepository imageRepository, FileConfig fileConfig) {
        this.imageRepository = imageRepository;
        this.root = Paths.get(fileConfig.getUploadDir());
        try {
            if(!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            throw new FileException(FileExceptionMessageUtils.ERROR_CREATE_ROOT_FOLDER + " " + e.getMessage());
        }
    }

    @Override
    public List<ImageDTO> findAll() {
        return imageRepository.findAll()
                .stream()
                .map(ImageMapper.INSTANCE::imageToImageDTO)
                .toList();
    }

    @Override
    public List<ImageDTO> uploadImage(UploadImageDTO uploadImageDTO) {
        checkFileInfoIsUnique(uploadImageDTO.getFiles());
        List<Image> images = new ArrayList<>();
        try {
            Stream.of(uploadImageDTO.getFiles()).forEach(file ->
                images.add(saveImage(file)));
            return images.stream()
                    .map(ImageMapper.INSTANCE::imageToImageDTO)
                    .toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteById(UUID id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundMessageUtils.IMAGE_ID_NOT_FOUND));
        deleteFileFromSystem(image.getName());
        imageRepository.delete(image);
    }

    private Image saveImage(MultipartFile file) {
        try {
            String uploadedFileName = file.getOriginalFilename();
            Path destinationFile = root.resolve(Paths.get(Objects.requireNonNull(uploadedFileName))).normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            String imageURL = MvcUriComponentsBuilder
                    .fromMethodName(FileRestController.class, "downloadFile", uploadedFileName)
                    .build()
                    .toString();
            Image image = new Image(uploadedFileName, imageURL);
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new ResourceNotFoundException(FileExceptionMessageUtils.UPLOAD_IMAGE_ERROR + " " + e.getMessage());
        }
    }

    private void checkFileInfoIsUnique(MultipartFile[] files) {
        Arrays.stream(files).forEach(file -> {
            if(file.isEmpty())
                throw new ResourceNotFoundException("Not found");
            if (imageRepository.findByName(file.getOriginalFilename()).isPresent())
                throw new FileException(FileExceptionMessageUtils.FILE_EXISTED);
        });
    }

    private void deleteFileFromSystem(String fileName) {
        try {
            Path path = root.resolve(Objects.requireNonNull(fileName));
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new ResourceNotFoundException(FileExceptionMessageUtils.DELETE_ERROR_FROM_ROOT + " " + e.getMessage());
        }
    }
}
