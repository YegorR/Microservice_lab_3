package ry.yegorr.audio_ms.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ry.yegorr.audio_ms.entity.*;

import java.util.*;

/**
 * User: RyazantsevEV<br>
 * Date: 27.12.2021<br>
 * Time: 19:22<br>
 */
@Repository
public interface AudioRepository extends JpaRepository<AudioEntity, Long> {
    Optional<AudioEntity> findByTrackId(Long trackId);
}
