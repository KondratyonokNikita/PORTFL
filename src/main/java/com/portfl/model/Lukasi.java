package com.portfl.model;

import javax.persistence.*;

/**
 * Created by Pokemon on 27.04.2017.
 */
@Entity
@Table(name = "lukasi")
public class Lukasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long photoId;

    private Long userId;

    private int lukas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getLukas() {
        return lukas;
    }

    public void setLukas(int lukas) {
        this.lukas = lukas;
    }
}
