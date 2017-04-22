package com.portfl.service;

import com.portfl.model.Photo;
import com.portfl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by Samsung on 21.04.2017.
 */
@Service
public class PhotoService {
    @Autowired
    private UserService userService;

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
}
