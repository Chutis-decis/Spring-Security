package com.example.demo.service;


import com.example.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface UserRepository extends JpaRepository<UserEntity, String> {
}
