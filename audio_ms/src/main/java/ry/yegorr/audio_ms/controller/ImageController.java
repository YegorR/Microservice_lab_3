package ry.yegorr.audio_ms.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import ry.yegorr.audio_ms.exception.*;
import ry.yegorr.audio_ms.service.*;

/**
 * User: egorr<br>
 * Date: 27.12.2021<br>
 * Time: 19:50<br>
 * todo name and feature
 */
@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/image")
    public Long loadImage(@RequestParam("image") MultipartFile file)
            throws ApplicationException {
        return imageService.saveImage(file);
    }

    @GetMapping(value = "/image/{image_id}", produces = "image/png")
    public ResponseEntity<?> getImage(@PathVariable("image_id") Long imageId) throws ApplicationException {
        byte[] file = imageService.getImage(imageId);
        return ResponseEntity.ok().body(file);
    }
}
