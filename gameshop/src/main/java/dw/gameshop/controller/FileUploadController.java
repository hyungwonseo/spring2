package dw.gameshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path copyLocation = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), copyLocation);
            return "File uploaded successfully: " + file.getOriginalFilename();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
            return "File already exist";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }
}
