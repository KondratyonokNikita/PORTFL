package com.portfl.dao;

import com.portfl.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Samsung on 14.04.2017.
 */
public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
