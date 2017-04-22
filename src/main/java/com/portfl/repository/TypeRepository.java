package com.portfl.repository;

import com.portfl.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Samsung on 14.04.2017.
 */
@Repository
@RepositoryRestResource(path = "types", collectionResourceRel = "types")
public interface TypeRepository extends JpaRepository<Type, Long> {
}
