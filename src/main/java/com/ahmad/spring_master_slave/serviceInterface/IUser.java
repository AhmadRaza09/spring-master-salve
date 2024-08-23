package com.ahmad.spring_master_slave.serviceInterface;


import com.ahmad.spring_master_slave.entity.User;

import java.util.List;

public interface IUser {
    User addOrUpdateUser(User user);

    List<User> giveAllUser();
}
