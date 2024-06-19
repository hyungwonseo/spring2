package dw.gameshop.repository;

import dw.gameshop.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
    ImageFile findByFilename(String filename);
}