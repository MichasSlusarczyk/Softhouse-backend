package pl.polsl.softhouse.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.polsl.softhouse.dto.user.UserAuthDto;
import pl.polsl.softhouse.dto.user.UserGetDto;
import pl.polsl.softhouse.dto.user.UserPostDto;
import pl.polsl.softhouse.services.UserService;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/users")
@ControllerAdvice
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
    public ResponseEntity<UserGetDto> authorizeUser(@RequestBody UserAuthDto authDto) {
        UserGetDto userDto =
                userService.authorizeUser(authDto.getUsername(), authDto.getPassword());

        return ResponseEntity.ok().body(userDto);
    }
}
