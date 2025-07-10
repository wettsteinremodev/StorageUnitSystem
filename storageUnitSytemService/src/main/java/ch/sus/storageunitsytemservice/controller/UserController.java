package ch.sus.storageunitsytemservice.controller;

import ch.sus.storageunitsytemservice.model.User;
import ch.sus.storageunitsytemservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all Users
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    // Create a new User
    @PostMapping("")
    public String createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return "User saved successfully";
    }

    // Update existing User
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) {
        updatedUser.setId(id);
        userService.updateById(updatedUser);
        return "User updated successfully";
    }

    // Delete User by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "User deleted successfully";
    }
}
