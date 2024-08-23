package com.ahmad.spring_master_slave.controller;

import com.ahmad.spring_master_slave.DTOs.UserDTO;
import com.ahmad.spring_master_slave.entity.User;
import com.ahmad.spring_master_slave.serviceInterface.IUser;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUser iUser;

    private final ModelMapper modelMapper;

    public UserController(IUser iUser, ModelMapper modelMapper) {
        this.iUser = iUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(iUser.giveAllUser(), HttpStatus.OK);
    }

    @GetMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody UserDTO user) {
        User saveUser = modelMapper.map(user, User.class);
        return new ResponseEntity(iUser.addOrUpdateUser(saveUser), HttpStatus.OK);
    }
}
