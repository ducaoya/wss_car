package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionDao extends JpaRepository<CollectionEntity, String> {
    List<CollectionEntity> findAllByByUser(String user);
}
