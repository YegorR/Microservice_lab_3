package ry.yegorr.audio_ms.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ry.yegorr.audio_ms.entity.*;

/**
 * User: egorr<br>
 * Date: 27.12.2021<br>
 * Time: 19:23<br>
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
