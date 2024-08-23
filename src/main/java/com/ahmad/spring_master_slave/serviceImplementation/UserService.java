package com.ahmad.spring_master_slave.serviceImplementation;

import com.ahmad.spring_master_slave.entity.User;
import com.ahmad.spring_master_slave.readRepository.UserReadRepository;
import com.ahmad.spring_master_slave.serviceInterface.IUser;
import com.ahmad.spring_master_slave.writeRepository.UserWriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUser {
    private final UserWriteRepository userWriteRepository;

    private final UserReadRepository userReadRepository;

    public UserService(UserWriteRepository userWriteRepository, UserReadRepository userReadRepository) {
        this.userWriteRepository = userWriteRepository;
        this.userReadRepository = userReadRepository;
    }

    @Override
    @Transactional
    public User addOrUpdateUser(User user) {
        return userWriteRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> giveAllUser() {
        return userReadRepository.findAll();
    }
}
