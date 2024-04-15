package com.app.vlog.Services;

import com.app.vlog.Dto.UserDto;

import java.util.List;

public interface UserService {

    //create user
   public UserDto createUser(UserDto userDto);

    //update User
    public UserDto updateUser(UserDto userDto, long userId);

    //Get User By Id
   public  UserDto getUserById(long userId);

    // Get All Users

    public List<UserDto> getAllUserBy();

    //delete user by id

    public void deleteUser(long userId);
}
