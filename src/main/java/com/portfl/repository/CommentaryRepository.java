package com.portfl.repository;

import com.portfl.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "commentaries", collectionResourceRel = "commentaries")
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    Iterable<Commentary> findAllByPhotoId(Long photoId);
}