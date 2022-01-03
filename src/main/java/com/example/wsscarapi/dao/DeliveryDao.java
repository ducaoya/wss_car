package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDao extends JpaRepository<DeliveryEntity,String> {
}
