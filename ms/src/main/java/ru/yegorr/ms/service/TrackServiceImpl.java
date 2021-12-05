package ru.yegorr.ms.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.entity.*;
import ru.yegorr.ms.exception.*;
import ru.yegorr.ms.repository.*;

import java.util.*;
import java.util.stream.*;

@Service
public class TrackServiceImpl implements TrackService {

  private final TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  @Override
  @Transactional
  public TrackDto getTrackInfo(Long trackId) throws ClientException {
    TrackEntity track = trackRepository.findById(trackId).orElseThrow(() ->
            new ClientException(HttpStatus.NOT_FOUND, "The track is not exist"));
    TrackDto response = new TrackDto();
    response.setId(trackId);
    response.setName(track.getName());
    response.setAlbumId(track.getAlbum().getAlbumId());
    response.setAlbumName(track.getAlbum().getName());
    response.setMusicianId(track.getAlbum().getMusician().getMusicianId());
    response.setMusicianName(track.getAlbum().getMusician().getName());

    return response;
  }

  @Override
  public List<TrackWithPlaysNumberDto> searchTracks(String query) {
    return trackRepository.findAllByNameContainingIgnoreCaseOrderByPlaysNumberDesc(query).
            stream().
            map((entity) -> {
              TrackWithPlaysNumberDto dto = new TrackWithPlaysNumberDto();
              dto.setId(entity.getTrackId());
              dto.setName(entity.getName());
              dto.setPlaysNumber(entity.getPlaysNumber());
              dto.setAlbumId(entity.getAlbum().getAlbumId());
              dto.setAlbumName(entity.getAlbum().getName());
              dto.setMusicianId(entity.getAlbum().getMusician().getMusicianId());
              dto.setMusicianName(entity.getAlbum().getMusician().getName());

              return dto;
            }).collect(Collectors.toList());
  }
}
