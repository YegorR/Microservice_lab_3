package ru.yegorr.ms.entity;

import javax.persistence.*;

@Entity
@Table(name = "track")
public class TrackEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "track_id")
  private Long trackId;

  @Column(name = "track_name", nullable = false)
  private String name;

  @Column(name = "track_order")
  private Integer order;

  @Column(name = "plays_number")
  private Integer playsNumber;

  @ManyToOne
  @JoinColumn(name = "album_id")
  private AlbumEntity album;

  public TrackEntity() {
  }

  public Long getTrackId() {
    return trackId;
  }

  public void setTrackId(Long trackId) {
    this.trackId = trackId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Integer getPlaysNumber() {
    return playsNumber;
  }

  public void setPlaysNumber(Integer playsNumber) {
    this.playsNumber = playsNumber;
  }

  public AlbumEntity getAlbum() {
    return album;
  }

  public void setAlbum(AlbumEntity album) {
    this.album = album;
  }
}
