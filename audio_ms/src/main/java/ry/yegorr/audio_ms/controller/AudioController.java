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
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping("/audio")
    public void loadAudio(@RequestParam("audio") MultipartFile file, @RequestParam("track_id") Long trackId)
            throws ApplicationException {
        audioService.saveAudio(trackId, file);
    }

    @GetMapping(value = "/audio/{track_id}", produces = "audio/mp3")
    public ResponseEntity<?> getAudio(@PathVariable("track_id") Long trackId) throws ApplicationException {
        byte[] file = audioService.getAudio(trackId);
        return ResponseEntity.ok().body(file);
    }
}
