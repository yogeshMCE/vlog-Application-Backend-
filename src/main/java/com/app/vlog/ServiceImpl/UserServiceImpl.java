package com.app.vlog.ServiceImpl;

import com.app.vlog.Dto.UserDto;
import com.app.vlog.Exceptions.ResourseNotFoundException;
import com.app.vlog.Models.User;
import com.app.vlog.Repositories.UserRepo;
import com.app.vlog.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
         User user= UserDtoToUser(userDto);
         User savedUser= userRepo.save(user);
         return UserToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, long userId) {
        User user = userRepo.findById(userId)
                            .orElseThrow(()-> new ResourseNotFoundException("user","Id",userId));
        User newUser= modelMapper.map(userDto,User.class);
        newUser.setId(userId);
        User savedUser = userRepo.save(newUser);
        return UserToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(long userId) {
        User user =userRepo.findById(userId)
                           .orElseThrow(()-> new ResourseNotFoundException("user","Id",userId));

        return UserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUserBy() {
       List<User> users= userRepo.findAll();
        return users.stream().map(this::UserToUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(long userId) {
        User user =userRepo.findById(userId)
                .orElseThrow(()-> new ResourseNotFoundException("user","Id",userId));
        userRepo.delete(user);

    }

    private User UserDtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
    private UserDto UserToUserDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}
