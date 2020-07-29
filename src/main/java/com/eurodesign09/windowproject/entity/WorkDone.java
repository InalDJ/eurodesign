package com.eurodesign09.windowproject.entity;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "work_done")
public class WorkDone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;
    @Column(name = "work_image")
    private byte[] workImage;
    @Column(name = "image_description")
    private String imageDescription;

    public WorkDone() {
    }

    public WorkDone(Integer imageId, byte[] workImage, String imageDescription) {
        this.imageId = imageId;
        this.workImage = workImage;
        this.imageDescription = imageDescription;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer image_id) {
        this.imageId = image_id;
    }

    public byte[] getWorkImage() {
        return workImage;
    }

    public void setWorkImage(byte[] image) {
        this.workImage = image;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(this.workImage);
    }
}
