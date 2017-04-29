package com.portfl.service;

import com.portfl.model.Rate;
import com.portfl.repository.RateRepository;
import javafx.beans.binding.DoubleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 27.04.2017.
 */
@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private UserService userService;

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
        if (count.equals(0)) {
            return Double.valueOf(0);
        } else {
            return rate / count;
        }
    }

    public Rate getMyRate(Long photoId) {
        return rateRepository.findOneByPhotoIdAndUserId(photoId, userService.getUser().getId());
    }
}
