package com.ipcamera.amcrest.repository;

import com.ipcamera.amcrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
