package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, String> {
    List<CommentEntity> findAllByCar(String car);
}
