package pl.polsl.softhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.polsl.softhouse.entities.User;
import pl.polsl.softhouse.services.UserService;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void addUser(User user) {
        userService.addUser(user);
    }

    @DeleteMapping(path="{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // TODO: A lot of parameters, probably not good.
    @PutMapping(path="{id}")
    public void updateUser(@PathVariable Long id,
                            @RequestParam(required=false) String username,
                            @RequestParam(required=false) String firstName,
                            @RequestParam(required=false) String lastName,
                            @RequestParam(required=false) String password,
                            @RequestParam(required=false) Boolean active) {

        userService.updateUser(id, firstName, lastName, username, password, active);
    }

    @GetMapping(path="username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
