package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.CarOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarOrderDao extends JpaRepository<CarOrderEntity,String> {
    List<CarOrderEntity> findAllByBuyer(String buyer);
    List<CarOrderEntity> findAllBySeller(String seller);
    List<CarOrderEntity> findAllById(String id);
}
