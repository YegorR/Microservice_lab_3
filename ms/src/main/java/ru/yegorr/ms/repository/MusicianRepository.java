package ru.yegorr.ms.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.yegorr.ms.entity.*;

import java.util.*;

@Repository
public interface MusicianRepository extends JpaRepository<MusicianEntity, Long> {
  List<MusicianEntity> findAllByNameContainingIgnoreCase(String search);

  List<MusicianEntity> findAllByNameStartingWithIgnoreCase(String letter);
}
