package com.portfl.repository;

import com.portfl.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 27.04.2017.
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findOneByPhotoIdAndUserId(Long photoId, Long userId);

    @Query("SELECT SUM(rate.rate) FROM Rate rate where rate.photoId = :photoId")
    Double getRateByPhotoId(@Param("photoId") Long photoId);

    Double countAllByPhotoId(Long photoId);
}
