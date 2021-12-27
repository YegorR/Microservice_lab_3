package ry.yegorr.audio_ms.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;
import ry.yegorr.audio_ms.entity.*;
import ry.yegorr.audio_ms.exception.*;
import ry.yegorr.audio_ms.repository.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * User: egorr<br>
 * Date: 27.12.2021<br>
 * Time: 19:25<br>
 * todo name and feature
 */
@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    private String rootPath;

    @Autowired
    public ImageService(ImageRepository imageRepository, @Value("${image.document-path}") String rootPath) {
        this.imageRepository = imageRepository;
        this.rootPath = rootPath;

        if (!this.rootPath.endsWith("/")) {
            this.rootPath += "/";
        }
    }

    public Long saveImage(MultipartFile file) throws ApplicationException {
        try {
            FileUtil.checkDirectory(rootPath);
            UUID name = UUID.randomUUID();
            Files.copy(file.getInputStream(), Paths.get(rootPath + name), StandardCopyOption.REPLACE_EXISTING);

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setAddress(rootPath + name);
            imageEntity = imageRepository.save(imageEntity);
            return imageEntity.getImageId();
        } catch (IOException exception) {
            throw new ApplicationException("Saving error file", exception);
        }
    }

    public byte[] getImage(Long imageId) throws ApplicationException {
        try {
            ImageEntity imageEntity = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("Image don't found"));
            return Files.readAllBytes(Paths.get(imageEntity.getAddress()));
        } catch (IOException exception) {
            throw new ApplicationException("Cannot get file", exception);
        }

    }
}
