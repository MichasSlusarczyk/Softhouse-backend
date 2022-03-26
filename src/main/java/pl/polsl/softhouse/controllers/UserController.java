package pl.polsl.softhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.polsl.softhouse.dto.user.UserAuthDto;
import pl.polsl.softhouse.dto.user.UserDto;
import pl.polsl.softhouse.dto.user.UserInfoDto;
import pl.polsl.softhouse.exceptions.user.UserAlreadyExistsException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        try {
            UserInfoDto userDto = userService.getUserById(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        try {
            userService.addUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            userService.updateUser(id, userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "username/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        try {
            UserAuthDto userDto = userService.getUserAuthByUsername(username);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
