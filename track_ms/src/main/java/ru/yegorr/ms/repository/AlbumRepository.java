package ru.yegorr.ms.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.yegorr.ms.entity.*;

import java.util.*;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
  List<AlbumEntity> findAllByNameContainingIgnoreCase(String search);
}
