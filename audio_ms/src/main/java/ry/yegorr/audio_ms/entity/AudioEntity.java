package ry.yegorr.audio_ms.entity;

import javax.persistence.*;

/**
 * User: RyazantsevEV<br>
 * Date: 27.12.2021<br>
 * Time: 19:14<br>
 * Track-entity
 */
@Entity
@Table(name = "audio")
public class AudioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audio_id")
    private Long audioId;

    @Column(name = "track_id")
    private Long trackId;

    @Column(name = "address")
    private String address;

    public AudioEntity() {
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
