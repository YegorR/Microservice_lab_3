package ru.yegorr.ms.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.yegorr.ms.dto.request.*;
import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.entity.*;
import ru.yegorr.ms.exception.*;
import ru.yegorr.ms.repository.*;

import java.util.*;
import java.util.stream.*;

@Service
public class AlbumServiceImpl implements AlbumService {
  private MusicianRepository musicianRepository;

  private TrackRepository trackRepository;

  private AlbumRepository albumRepository;

  @Override
  @Transactional
  public FullAlbumDto createAlbum(CreateAlbumDto createAlbumRequest, Long musicianId)
          throws ClientException {
    if (!musicianRepository.existsById(musicianId)) {
      throw new ClientException(HttpStatus.NOT_FOUND, "The musician is not exists");
    }

    AlbumEntity album = new AlbumEntity();
    album.setName(createAlbumRequest.getName());
    album.setReleaseDate(createAlbumRequest.getReleaseDate());
    MusicianEntity musician = musicianRepository.findById(musicianId).
            orElseThrow(() -> new ClientException(HttpStatus.NOT_FOUND,
                    "The musician is not exists"));
    album.setMusician(musician);
    album.setSingle(createAlbumRequest.isSingle());

    album = albumRepository.save(album);

    List<TrackEntity> tracks = new ArrayList<>();
    int order = 0;
    for (CreateAlbumDto.Track track : createAlbumRequest.getTracks()) {
      TrackEntity trackEntity = new TrackEntity();
      trackEntity.setName(track.getName());
      trackEntity.setAlbum(album);
      trackEntity.setOrder(order++);
      trackEntity.setPlaysNumber(0);
      tracks.add(trackEntity);
      trackRepository.save(trackEntity);
    }
    album.setTracks(tracks);

    album = albumRepository.save(album);
    return translateEntityToDto(album);
  }

  @Override
  @Transactional
  public FullAlbumDto changeAlbum(ChangeAlbumDto changeAlbumRequest, Long albumId)
          throws ClientException {
    Optional<AlbumEntity> albumOptional = albumRepository.findById(albumId);
    if (!albumOptional.isPresent()) {
      throw new ClientException(HttpStatus.NOT_FOUND, "The album is not exists");
    }
    AlbumEntity album = albumOptional.get();

    album.setName(changeAlbumRequest.getName());
    album.setReleaseDate(changeAlbumRequest.getReleaseDate());
    album.setSingle(changeAlbumRequest.isSingle());

    List<TrackEntity> oldTracks = album.getTracks();
    List<TrackEntity> newTracks = new ArrayList<>();

    int order = 0;
    for (ChangeAlbumDto.Track track : changeAlbumRequest.getTracks()) {
      Long id = track.getId();
      TrackEntity trackEntity;
      if (id == null) {
        trackEntity = new TrackEntity();
        trackEntity.setPlaysNumber(0);
      } else {
        Optional<TrackEntity> trackEntityOptional = trackRepository.findById(id);
        if (!trackEntityOptional.isPresent()) {
          throw new ClientException(HttpStatus.NOT_FOUND,
                  String.format("The track %d is not exist", id));
        }
        trackEntity = trackEntityOptional.get();
        oldTracks.removeIf(oldTrack -> oldTrack.getTrackId().equals(id));
      }
      trackEntity.setName(track.getName());
      trackEntity.setAlbum(album);
      trackEntity.setOrder(order++);
      newTracks.add(trackEntity);
    }
    trackRepository.deleteAll(oldTracks);
    trackRepository.saveAll(newTracks);
    album.setTracks(newTracks);
    album = albumRepository.save(album);

    return translateEntityToDto(album);
  }

  @Override
  @Transactional
  public FullAlbumDto getAlbum(Long albumId) throws ClientException {
    AlbumEntity album = albumRepository.findById(albumId)
            .orElseThrow(() -> new ClientException(HttpStatus.NOT_FOUND,
                    "The album is not exist"));
    return translateEntityToDto(album);
  }

  @Override
  @Transactional
  public void deleteAlbum(Long albumId) throws ClientException {
    if (!albumRepository.existsById(albumId)) {
      throw new ClientException(HttpStatus.NOT_FOUND, "The album is not exist");
    }
    albumRepository.deleteById(albumId);
  }

  @Override
  public List<BriefAlbumDescriptionDto> searchAlbums(String query) {
    List<AlbumEntity> albums = albumRepository.findAllByNameContainingIgnoreCase(query);
    return albums.stream().map(this::translateEntityToBriefDto).collect(Collectors.toList());
  }

  private BriefAlbumDescriptionDto translateEntityToBriefDto(AlbumEntity entity) {
    BriefAlbumDescriptionDto dto = new BriefAlbumDescriptionDto();
    dto.setId(entity.getAlbumId());
    dto.setName(entity.getName());
    dto.setReleaseDate(entity.getReleaseDate());
    dto.setSingle(entity.getSingle());

    BriefMusicianDto musician = new BriefMusicianDto();
    musician.setId(entity.getMusician().getMusicianId());
    musician.setName(entity.getMusician().getName());
    dto.setMusician(musician);

    return dto;
  }

  private FullAlbumDto translateEntityToDto(AlbumEntity entity) {
    FullAlbumDto dto = new FullAlbumDto();
    dto.setId(entity.getAlbumId());
    dto.setName(entity.getName());
    dto.setReleaseDate(entity.getReleaseDate());
    dto.setSingle(entity.getSingle());

    BriefMusicianDto musician = new BriefMusicianDto();
    musician.setId(entity.getMusician().getMusicianId());
    musician.setName(entity.getMusician().getName());
    dto.setMusician(musician);

    List<TrackDescriptionDto> tracks = new ArrayList<>();
    for (TrackEntity trackEntity : entity.getTracks()) {
      TrackDescriptionDto track = new TrackDescriptionDto();
      track.setId(trackEntity.getTrackId());
      track.setPlaysNumber(trackEntity.getPlaysNumber());
      track.setName(trackEntity.getName());
      tracks.add(track);
    }
    dto.setTracks(tracks);

    return dto;
  }

  @Autowired
  public void setMusicianRepository(MusicianRepository musicianRepository) {
    this.musicianRepository = musicianRepository;
  }

  @Autowired
  public void setTrackRepository(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  @Autowired
  public void setAlbumRepository(AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
  }
}
