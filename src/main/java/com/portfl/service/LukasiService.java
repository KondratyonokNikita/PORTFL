package com.portfl.service;

import com.portfl.model.Lukasi;
import com.portfl.repository.LukasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 27.04.2017.
 */
@Service
public class LukasiService {
    @Autowired
    private LukasiRepository lukasiRepository;

    @Autowired
    private UserService userService;

    public boolean addLukas(Long photoId, Long userId) {
        Lukasi lukasi = lukasiRepository.findOneByPhotoIdAndUserId(photoId, userId);
        if (lukasi != null) {
            if(lukasi.getLukas()==-1){
                lukasi.setLukas(1);
                lukasiRepository.save(lukasi);
            }
            else
                return false;
        }
        else {
            lukasi = new Lukasi();
            lukasi.setPhotoId(photoId);
            lukasi.setUserId(userId);
            lukasi.setLukas(1);
            lukasiRepository.save(lukasi);
        }
        return true;
    }

    public boolean addDizLukas(Long photoId, Long userId) {
        Lukasi lukasi = lukasiRepository.findOneByPhotoIdAndUserId(photoId, userId);
        if (lukasi != null) {
            if(lukasi.getLukas()==1){
                lukasi.setLukas(-1);
                lukasiRepository.save(lukasi);
            }
            else
                return false;
        }
        else {
            lukasi = new Lukasi();
            lukasi.setPhotoId(photoId);
            lukasi.setUserId(userId);
            lukasi.setLukas(-1);
            lukasiRepository.save(lukasi);
        }
        return true;
    }

    public Long getAllLukas(Long photoId) {
        return lukasiRepository.sumByLukas(photoId);
    }

    public void initialiseLukas(Long photoId){
        Lukasi lukasi=new Lukasi();
        lukasi.setPhotoId(photoId);
        lukasi.setUserId(userService.getUser().getId());
        lukasi.setLukas(1);
        lukasiRepository.save(lukasi);
    }
}
