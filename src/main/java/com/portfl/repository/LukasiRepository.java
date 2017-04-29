package com.portfl.repository;

import com.portfl.model.Lukasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 27.04.2017.
 */
@Repository
@RepositoryRestResource(path = "lukasi", collectionResourceRel = "lukasi")
public interface LukasiRepository extends JpaRepository<Lukasi, Long> {
    Lukasi findOneByPhotoIdAndUserId(Long photoId, Long userId);

    @Query("SELECT SUM(lukas) FROM Lukasi lukasi where lukasi.photoId = :photoId")
    Long sumByLukas(@Param("photoId") Long photoId);
}
