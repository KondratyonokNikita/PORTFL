package com.portfl.dao;

import com.portfl.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Samsung on 13.04.2017.
 */
public interface GenderDao extends JpaRepository<Gender, Long> {
}
