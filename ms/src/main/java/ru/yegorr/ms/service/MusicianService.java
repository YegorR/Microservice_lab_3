package ru.yegorr.ms.service;

import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.exception.*;

import java.util.*;

public interface MusicianService {
  MusicianDto createMusician(String name, String description);

  MusicianDto changeMusician(Long id, String name, String description) throws ClientException;

  void deleteMusician(Long id) throws ClientException;

  MusicianDto getMusician(Long id) throws ClientException;

  List<BriefMusicianDto> searchMusicians(String query);
}
