package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.model.UserMaster;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserMaster, Long> {
    UserDetails findByUsername(String username);
    boolean existsByUsername(String username);
}
