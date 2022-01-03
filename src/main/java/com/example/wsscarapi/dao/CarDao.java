package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<CarEntity, String> {
    Page<CarEntity> findAll(Pageable pageable);
    Page<CarEntity> findAll(Specification<CarEntity> specification, Pageable pageable);
}
