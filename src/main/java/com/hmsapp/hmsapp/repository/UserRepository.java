package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User>  findByEmail(String email);
    Optional<User> findBymobile(String mobile);
    Optional<User> findByUsername(String username);


    Optional<User> findByMobile(String mobile);
}