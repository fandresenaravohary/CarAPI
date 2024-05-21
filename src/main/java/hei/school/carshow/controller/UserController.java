package hei.school.carshow.controller;

import hei.school.carshow.db.entity.LoginRequest;
import hei.school.carshow.db.entity.User;
import hei.school.carshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/Users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/User/{id}")
    public User getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @PostMapping("/User")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/User/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid username or password");
        }
    }

    @PostMapping("/User/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>("User created with success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error on creating user", HttpStatus.OK);
        }
    }

    @PutMapping("/admin/User/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updateUser) {
        User updatedUser = userService.updateUser(id, updateUser);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/User/Delete/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }
}
