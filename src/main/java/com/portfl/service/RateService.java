package com.portfl.service;

import com.portfl.model.Photo;
import com.portfl.model.Rate;
import com.portfl.repository.PhotoRepository;
import com.portfl.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    public void addRate(Long photoId, Long rate) {
        if (userService.getUser() == null)
            return;
        Rate curRate = rateRepository.findOneByPhotoIdAndUserId(photoId, userService.getUser().getId());
        if (rate == -1) {
            rateRepository.delete(curRate);
            return;
        }
        if (curRate == null) {
            curRate = new Rate();
            curRate.setPhotoId(photoId);
            curRate.setUserId(userService.getUser().getId());
        }
        curRate.setRate(rate);
        rateRepository.save(curRate);
    }

    public Double getRate(Long photoId) {
        Double rate = rateRepository.getRateByPhotoId(photoId);
        Double count = rateRepository.countAllByPhotoId(photoId);
        if ((count.equals(0)) || (rate == null)) {
            return Double.valueOf(0);
        } else {
            return rate / count;
        }
    }

    public Rate getMyRate(Long photoId) {
        if (userService.getUser() == null)
            return null;
        return rateRepository.findOneByPhotoIdAndUserId(photoId, userService.getUser().getId());
    }

    public List<Photo> getTopPhotos(int count) {
        photoService.updateRate();
        List<Photo> photos = photoService.findAll();
        Collections.sort(photos, Comparator.comparing(Photo::getRate));
        Collections.reverse(photos);
        if (photos.size() < count) {
            return photos;
        } else {
            return photos.subList(0, count);
        }
    }
}
