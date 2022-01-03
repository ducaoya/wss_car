package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<CarEntity, String> {
    List<CarEntity> findAllByTitleLike(String title);
}
