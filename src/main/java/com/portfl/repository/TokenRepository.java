package com.portfl.repository;

import com.portfl.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Vlad on 22.03.17.
 */
@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long>{
    VerificationToken findByToken(String token);
}
