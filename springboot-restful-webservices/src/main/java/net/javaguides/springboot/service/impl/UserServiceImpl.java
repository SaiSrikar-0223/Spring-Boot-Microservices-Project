package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert userDto to User JPA Entity
        //User user = UserMapper.mapToUser(userDto);

        //User user = modelMapper.map(userDto,User.class);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw  new EmailAlreadyExistException("Email Already Exist for a User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        //Convert User JPA entity to UserDto
        //UserDto saveduserDto =UserMapper.mapToUserDto(savedUser);

        //UserDto saveduserDto =modelMapper.map(savedUser,UserDto.class);

        UserDto saveduserDto =AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return saveduserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
         //Optional<User> optionalUser = userRepository.findById(userId);
          User user = userRepository.findById(userId).orElseThrow(
                  ()-> new ResourceNotFoundException("User","id",userId));
         //User user = optionalUser.get();
         //return UserMapper.mapToUserDto(user);
         //return modelMapper.map(user,UserDto.class);
         //return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
         return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

//        return users.stream().map((user) -> modelMapper.map(user,UserDto.class))
//                .collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        //User existingUser = userRepository.findById(user.getId()).get();
        User existingUser = userRepository.findById(user.getId()).
                orElseThrow(()->new ResourceNotFoundException("User","id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateduser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updateduser);
        //return modelMapper.map(updateduser,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updateduser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User","id", userId));
        //userRepository.deleteById(userId);
        userRepository.deleteById(userId);
    }
}
