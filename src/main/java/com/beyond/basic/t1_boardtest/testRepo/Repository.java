package com.beyond.basic.t1_boardtest.testRepo;

import com.beyond.basic.t1_boardtest.testDomain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
