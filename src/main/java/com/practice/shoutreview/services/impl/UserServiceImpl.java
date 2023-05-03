package com.practice.shoutreview.services.impl;

import com.practice.shoutreview.dto.UserDto;
import com.practice.shoutreview.entities.User;
import com.practice.shoutreview.exceptions.ResourceNotFoundException;
import com.practice.shoutreview.repositories.UserRepository;
import com.practice.shoutreview.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDto addUser(UserDto userDto) {

        if (userRepository.existsByMobile(userDto.getMobile())) {
            return userDto;
        }

        User user = User.DtoToUser(userDto);

        user = userRepository.save(user);

       // log.info("Added New User"+user.toString());

        return User.UserToDto(user);
    }


    public UserDto getUser(Integer id) {
        Optional<User> userEntity = userRepository.findById(id);

        if (userEntity.isEmpty()) {
            //log.error("User not found for id: " + id);
            throw new ResourceNotFoundException("User","Id",id);

        }

        return User.UserToDto(userEntity.get());
    }

}
