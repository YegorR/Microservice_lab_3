package ru.yegorr.ms.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.yegorr.ms.dto.request.*;
import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.exception.*;
import ru.yegorr.ms.service.*;

import java.util.*;

@RestController
public class AlbumController {

  private final AlbumService albumService;

  @Autowired
  public AlbumController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @PostMapping("/musician/{musicianId}/album")
  public ResponseEntity<?> createAlbum(@RequestBody CreateAlbumDto request,
                                       @PathVariable("musicianId") Long musicianId)
          throws Exception {
    FullAlbumDto response = albumService.createAlbum(request, musicianId);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/album/{albumId}")
  public ResponseEntity<?> changeAlbum(@RequestBody ChangeAlbumDto request,
                                       @PathVariable("albumId") Long albumId)
          throws Exception {
    FullAlbumDto response = albumService.changeAlbum(request, albumId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/album/{albumId}")
  public ResponseEntity<?> getAlbum(@PathVariable("albumId") Long albumId)
          throws Exception {
    FullAlbumDto response = albumService.getAlbum(albumId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("album/{albumId}")
  public ResponseEntity<?> deleteAlbum(@PathVariable("albumId") Long albumId)
          throws Exception {
    albumService.deleteAlbum(albumId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(path = "albums")
  public ResponseEntity<?> search(@RequestParam("query") String query) throws Exception {
    query = query.trim();
    if (query.isEmpty()) {
      throw new ClientException("No query");
    }
    List<BriefAlbumDescriptionDto> response = albumService.searchAlbums(query);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
