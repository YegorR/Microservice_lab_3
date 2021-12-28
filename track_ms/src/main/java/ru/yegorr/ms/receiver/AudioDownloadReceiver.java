package ru.yegorr.ms.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.yegorr.ms.entity.*;
import ru.yegorr.ms.repository.*;
import ru.yegorr.ms.service.*;

import java.util.*;

/**
 * User: egorr<br>
 * Date: 28.12.2021<br>
 * Time: 1:08<br>
 * todo name and feature
 */
@Component
public class AudioDownloadReceiver {
    private TrackRepository trackRepository;

    @Autowired
    public AudioDownloadReceiver(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Transactional
    @RabbitListener(queues = "${queue.download-audio-queue}")
    public void audioHasDownloaded(String trackIdMsg) {
        Long trackId = Long.valueOf(trackIdMsg);
        Optional<TrackEntity> trackEntityOptional = trackRepository.findById(trackId);
        if (!trackEntityOptional.isPresent()) {
            return;
        }
        TrackEntity trackEntity = trackEntityOptional.get();
        if (trackEntity.getPlaysNumber() == null) {
            trackEntity.setPlaysNumber(0);
        }
        trackEntity.setPlaysNumber(trackEntity.getPlaysNumber()+ 1);
        trackRepository.save(trackEntity);
    }
}
