package com.portfl.repository;

import com.portfl.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Samsung on 13.04.2017.
 */
@Repository
@RepositoryRestResource(path = "photos", collectionResourceRel = "photos")
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
