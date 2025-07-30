package com.project.level2_bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.level2_bookreview.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}