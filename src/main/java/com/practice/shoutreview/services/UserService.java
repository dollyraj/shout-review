package com.practice.shoutreview.services;

import com.practice.shoutreview.dto.UserDto;

public interface UserService {
     UserDto addUser(UserDto userDto);
     UserDto getUser(Integer id);
}
