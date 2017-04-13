package com.portfl.dao;

import com.portfl.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Samsung on 13.04.2017.
 */
public interface PhotoDao extends JpaRepository<Photo, Long> {
}
