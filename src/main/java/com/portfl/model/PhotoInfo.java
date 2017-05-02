package com.portfl.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

public class PhotoInfo {
    private Long id;
    private String path;
    private Integer height;
    private Integer width;
    private Integer bytes;
    private String originalFilename;
    private Instant createdAt;
    private Instant updated;

    public PhotoInfo(Photo photo) {
        this.id = photo.getId();
        this.path = photo.getPath();
        this.height = photo.getHeight();
        this.width = photo.getWidth();
        this.bytes = photo.getBytes();
        this.originalFilename = photo.getOriginalFilename();
        this.createdAt = photo.getCreatedAt();
        this.createdAt = photo.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getBytes() {
        return bytes;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }
}
