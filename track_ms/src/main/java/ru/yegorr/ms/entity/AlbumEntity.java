package ru.yegorr.ms.entity;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "album")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "album_name", nullable = false, length = 64)
    private String name;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;


    @Column(name = "is_single")
    private Boolean single;

    @ManyToOne
    @JoinColumn(name = "musician_id", nullable = false)
    private MusicianEntity musician;

    @OneToMany(mappedBy = "album")
    @OrderBy("order")
    private List<TrackEntity> tracks;

    public AlbumEntity() {
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseYear) {
        this.releaseDate = releaseYear;
    }

    public Boolean getSingle() {
        return single;
    }

    public void setSingle(Boolean single) {
        this.single = single;
    }

    public MusicianEntity getMusician() {
        return musician;
    }

    public void setMusician(MusicianEntity musician) {
        this.musician = musician;
    }

    public List<TrackEntity> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackEntity> albums) {
        this.tracks = albums;
    }
}
