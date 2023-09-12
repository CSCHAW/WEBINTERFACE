package com.ipcamera.logging.repository;

import com.ipcamera.logging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select count(u)>0 from User u where u.username=:username and u.password =:password")
    Boolean isUserExist(@Param("username") String username, @Param("password") String password);
}
