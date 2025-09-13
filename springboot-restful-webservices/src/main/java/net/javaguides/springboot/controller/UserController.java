package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resources",
        description = "CRUD Rest APIs-Create User ,Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

   private UserService userService;

   @Operation(
           summary = "Create User REST API",
           description = "Create User Rest API is used to save user in a database"
   )
   @ApiResponse(
           responseCode = "201",
           description = "HTTP Status 201 Created"
   )
   //build create User Rest API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User By Id REST API",
            description = "Get User By Id Rest API is used to get single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    //build get User by Id Rest API
    //http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @Operation(
            summary = "Get AllUsers REST API",
            description = "Get AllUsers Rest API is used to get All user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )

    //build Get All Users Rest API
    //http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User Rest API is used to update the user fo the given Id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    //build Update User Rest API
    //http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User Rest API is used to delete the user fo the given Id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )

    //build Delete User Rest API
      @DeleteMapping("{id}")
      public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Successfully Deleted..!!",HttpStatus.OK);
      }

//      @ExceptionHandler(ResourceNotFoundException.class)
//      public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
//              ResourceNotFoundException exception , WebRequest webRequest){
//        ErrorDetails errorDetails =new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER NOT FOUND"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//      }
}
