package ru.yegorr.ms.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.service.*;

import java.util.*;

@RestController
public class TrackController {
  private final TrackService trackService;


  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }


  @GetMapping(path = "/track/{trackId}")
  public ResponseEntity<?> getTrackInfo(@PathVariable Long trackId)
          throws Exception {
    TrackDto trackDto = trackService.getTrackInfo(trackId);
    return new ResponseEntity<>(trackDto, HttpStatus.OK);
  }

  @GetMapping(path = "/tracks")
  public ResponseEntity<?> searchTracks(@RequestParam String query)
          throws Exception {

    List<TrackWithPlaysNumberDto> result = trackService.searchTracks(query);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
