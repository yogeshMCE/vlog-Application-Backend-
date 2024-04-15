package com.app.vlog.Controllers;

import com.app.vlog.Dto.Response;
import com.app.vlog.Dto.UserDto;
import com.app.vlog.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    // create user

    @PostMapping("/")
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        Response response= new Response(createdUser,true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("user_id") long id){
        UserDto createdUser = userService.updateUser(userDto,id);
        Response response= new Response(createdUser,true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<Response> getUserById(@PathVariable("user_id") long id){
        UserDto User = userService.getUserById(id);
        Response response= new Response(User,true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAllUsers(){
        List<UserDto> Users = userService.getAllUserBy();
        Response response= new Response(Users,true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable("user_id") long id){
        userService.deleteUser(id);
        Response response= new Response("User Deleted Successfully",true);
         return new ResponseEntity<>(response,HttpStatus.OK);


    }

}