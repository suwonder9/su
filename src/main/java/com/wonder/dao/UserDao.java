package com.wonder.dao;

import com.wonder.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, String> {

    UserEntity findByAccount(String account);
}
