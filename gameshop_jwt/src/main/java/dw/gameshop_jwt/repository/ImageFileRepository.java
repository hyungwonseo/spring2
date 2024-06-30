package dw.gameshop_jwt.repository;

import dw.gameshop_jwt.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
    ImageFile findByFilename(String filename);
}