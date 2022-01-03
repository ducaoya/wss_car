package com.example.wsscarapi.dao;

import com.example.wsscarapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
    UserEntity findByPhone(String phone);
    UserEntity findByUsername(String username);
}
