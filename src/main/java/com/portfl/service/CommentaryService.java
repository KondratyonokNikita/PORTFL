package com.portfl.service;

import com.portfl.model.Commentary;
import com.portfl.repository.CommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryService {

    @Autowired
    CommentaryRepository commentaryRepository;

    public Commentary findOne(Long photoId) {
        return this.commentaryRepository.findOne(photoId);
    }

    public void addCommenatry(Commentary commentary){
        this.commentaryRepository.save(commentary);
    }

    public Iterable<Commentary> findAll() {
        return this.commentaryRepository.findAll();
    }

    public Iterable<Commentary> findAllByPhotoId(Long photoId) {
        return this.commentaryRepository.findAllByPhotoId(photoId);
    }
}
