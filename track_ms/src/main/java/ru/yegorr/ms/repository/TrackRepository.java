package ru.yegorr.ms.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.yegorr.ms.entity.*;

import java.util.*;

/**
 * User: egorr<br>
 * Date: 22.11.2021<br>
 * Time: 19:04<br>
 * todo name and feature
 */
@Repository
public interface TrackRepository extends JpaRepository<TrackEntity, Long> {
    List<TrackEntity> findAllByNameContainingIgnoreCaseOrderByPlaysNumberDesc(String search);
}

