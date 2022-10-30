package cybersoft.javabackend.java18.obajuecommerce.app_image.repository;

import cybersoft.javabackend.java18.obajuecommerce.app_image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Optional<Image> findByName(String name);
}
