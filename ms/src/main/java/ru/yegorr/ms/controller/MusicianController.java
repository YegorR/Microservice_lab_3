package ru.yegorr.ms.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;;
import ru.yegorr.ms.dto.request.*;
import ru.yegorr.ms.dto.response.*;
import ru.yegorr.ms.service.*;

import java.util.*;

@RestController
public class MusicianController {

    private final MusicianService musicianService;

    @Autowired
    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @PostMapping(path = "/musician", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createMusician(@RequestBody CreateMusicianDto createMusicianDto)
            throws Exception {
        MusicianDto musicianResponseDto = musicianService.createMusician(createMusicianDto.getName(), createMusicianDto.
                getDescription());
        return new ResponseEntity<>(musicianResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/musician/{musicianId}")
    public ResponseEntity<?> changeMusician(
            @RequestBody CreateMusicianDto createMusicianDto,
            @PathVariable("musicianId") Long musicianId
    )
            throws Exception {
        MusicianDto musicianResponseDto = musicianService.changeMusician(musicianId,
                createMusicianDto.getName(), createMusicianDto.getDescription()
        );

        return new ResponseEntity<>(musicianResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/musician/{musicianId}")
    public ResponseEntity<?> deleteMusician(@PathVariable("musicianId") Long musicianId)
            throws Exception {

        musicianService.deleteMusician(musicianId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/musician/{musicianId}")
    public ResponseEntity<?> getMusician(@PathVariable("musicianId") Long musicianId)
            throws Exception {
        MusicianDto musicianDto = musicianService.getMusician(musicianId);
        return new ResponseEntity<>(musicianDto, HttpStatus.OK);
    }

    @GetMapping("/musicians")
    public ResponseEntity<?> searchMusicians(@RequestParam("query") String query)
            throws Exception {
        List<BriefMusicianDto> result = musicianService.searchMusicians(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
