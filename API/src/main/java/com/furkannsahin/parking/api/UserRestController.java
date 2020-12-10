package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.UserDto;
import com.furkannsahin.parking.mapper.UserMapper;
import com.furkannsahin.parking.model.User;
import com.furkannsahin.parking.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Api(value = "User Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class UserRestController {

    @Autowired
    private final UserService userService;

    private final UserMapper userMapper;

    @ApiOperation(value = "Get users", notes ="returns users")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(userMapper.toUserDtos(users));
    }

    @ApiOperation(value = "Create an user", notes ="Creates an user and returns it")
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        try{
            userDto.setPassword(new String(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes())));
            User createdUser = userService.createUser(userMapper.toUser(userDto));
            return ResponseEntity.ok(userMapper.toUserDto(createdUser));
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
