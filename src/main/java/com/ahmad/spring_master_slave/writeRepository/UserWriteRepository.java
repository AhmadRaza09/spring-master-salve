package com.ahmad.spring_master_slave.writeRepository;

import com.ahmad.spring_master_slave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWriteRepository extends JpaRepository<User, Long> {
}
