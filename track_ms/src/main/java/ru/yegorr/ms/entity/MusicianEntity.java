package ru.yegorr.ms.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "musician")
public class MusicianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "musician_id")
    private Long musicianId;

    @Column(name = "musician_name", nullable = false, length = 64)
    private String name;

    @Column(name = "musician_description", length = 4096)
    private String description;

    @OneToMany(mappedBy = "musician")
    @OrderBy("releaseDate")
    private List<AlbumEntity> albums;

    public MusicianEntity() {
    }

    public Long getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(Long musicianId) {
        this.musicianId = musicianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumEntity> albums) {
        this.albums = albums;
    }
}