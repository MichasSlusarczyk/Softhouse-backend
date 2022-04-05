package pl.polsl.softhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.polsl.softhouse.dto.user.UserGetDto;
import pl.polsl.softhouse.dto.user.UserPostDto;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.user.UserException;
import pl.polsl.softhouse.services.UserService;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDto>> getAll() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<URI> addUser(@RequestBody UserPostDto userDto) {
        long userId = userService.addUser(userDto);
        URI createdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserPostDto userDto) {
        userService.updateUser(id, userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "auth")
    public ResponseEntity<UserGetDto> authorizeUser(@RequestBody Map<String, String> json) {

        String username = json.get("username"),
                password = json.get("password");

        return ResponseEntity.ok().body(userService.authorizeUser(username, password));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations()
                .forEach((violation) -> errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserExceptions(UserException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
