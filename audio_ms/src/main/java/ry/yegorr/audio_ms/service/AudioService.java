package ry.yegorr.audio_ms.service;

import org.springframework.amqp.rabbit.core.*;
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
 */
@Service
@Transactional
public class AudioService {

    private final AudioRepository audioRepository;

    private final String downloadAudioQueue;

    private String rootPath;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AudioService(
            AudioRepository audioRepository, @Value("${audio.document-path}") String rootPath, @Value("${queue.download-audio-queue}") String downloadAudioQueue
    ) {
        this.audioRepository = audioRepository;
        this.rootPath = rootPath;

        if (!this.rootPath.endsWith("/")) {
            this.rootPath += "/";
        }

        this.downloadAudioQueue = downloadAudioQueue;
    }

    public void saveAudio(Long trackId, MultipartFile file) throws ApplicationException {
        try {
            FileUtil.checkDirectory(rootPath);
            UUID name = UUID.randomUUID();
            Files.copy(file.getInputStream(), Paths.get(rootPath + name), StandardCopyOption.REPLACE_EXISTING);

            AudioEntity audioEntity = new AudioEntity();
            audioEntity.setAddress(rootPath + name);
            audioEntity.setTrackId(trackId);
            audioRepository.save(audioEntity);
        } catch (IOException exception) {
            throw new ApplicationException("Saving error file", exception);
        }
    }

    public byte[] getAudio(Long trackId) throws ApplicationException {
        try {
            AudioEntity audioEntity = audioRepository.findByTrackId(trackId).orElseThrow(() -> new ResourceNotFoundException("Track don't found"));
            byte[] result =  Files.readAllBytes(Paths.get(audioEntity.getAddress()));
            rabbitTemplate.convertAndSend(downloadAudioQueue, trackId);
            return result;
        } catch (IOException exception) {
            throw new ApplicationException("Cannot get file", exception);
        }
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
