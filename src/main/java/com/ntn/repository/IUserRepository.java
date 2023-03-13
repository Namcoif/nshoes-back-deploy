package com.ntn.repository;

import com.ntn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query("SELECT ac FROM User ac WHERE ac.username=:usernameParameter")
    User findByUsername(@Param("usernameParameter") String username);

//    @Query("SELECT ac FROM User ac WHERE ac.email=: emailParameter")
//    User findByEmail(@Param("emailParameter") String email);
}
