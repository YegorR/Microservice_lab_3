package ry.yegorr.audio_ms.entity;

import javax.persistence.*;

/**
 * User: RyazantsevEV<br>
 * Date: 27.12.2021<br>
 * Time: 19:19<br>
 * Image entity
 */
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "address")
    private String address;

    public ImageEntity() {
    }

    public Long getImageId() {
        return imageId;
    }

    public String getAddress() {
        return address;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
