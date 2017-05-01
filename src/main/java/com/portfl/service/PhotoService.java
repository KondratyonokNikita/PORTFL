package com.portfl.service;

import com.portfl.model.Photo;
import com.portfl.model.User;
import com.portfl.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RateService rateService;

    public Photo findOne(Long photoId) {
        return this.photoRepository.findOne(photoId);
    }

    public void addPhotos(List<Map<String, Object>> photos) {
        User user = userService.getUser();
        List<Photo> userPhotos = user.getPhotos();
        for(Map<String, Object> info: photos) {
            Photo photo = new Photo();
            photo.setPath((String)info.get("path"));
            photo.setUser(user);
            photo.setCreatedAt(Instant.parse((String)info.get("created_at")));
            photo.setUpdated(Instant.parse((String)info.get("created_at")));
            photo.setBytes((Integer)info.get("bytes"));
            photo.setHeight((Integer)info.get("height"));
            photo.setWidth((Integer)info.get("width"));
            photo.setOriginalFilename((String)info.get("original_filename"));
            userPhotos.add(photo);
        }
        userService.update(user);
    }

    public void updateRate() {
        List<Photo> photos = photoRepository.findAll();
        for (Photo photo : photos) {
            photo.setRate(rateService.getRate(photo.getId()));
        }
        photoRepository.save(photos);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }
}
