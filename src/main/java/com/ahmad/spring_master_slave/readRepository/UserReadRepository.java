package com.ahmad.spring_master_slave.readRepository;

import com.ahmad.spring_master_slave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReadRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(String email);
}
