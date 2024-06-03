package hei.school.carshow.controller;

import hei.school.carshow.db.entity.User;
import hei.school.carshow.exception.NotFoundException;
import hei.school.carshow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/admin/Users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/User/{id}")
    public User getUserById(@PathVariable UUID id){
        return userService.getUserById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/User")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
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

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId){
        return userService.getUserById(UUID.fromString(userId)).orElseThrow(NotFoundException::new);
    }

}
