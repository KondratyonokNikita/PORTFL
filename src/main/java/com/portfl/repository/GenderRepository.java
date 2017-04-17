package com.portfl.repository;

import com.portfl.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Samsung on 13.04.2017.
 */
@Repository
@RepositoryRestResource(path = "genders", collectionResourceRel = "genders")
public interface GenderRepository extends JpaRepository<Gender, Long> {
}
