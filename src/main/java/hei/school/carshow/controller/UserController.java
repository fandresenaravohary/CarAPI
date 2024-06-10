package hei.school.carshow.controller;

import hei.school.carshow.entity.User;
import hei.school.carshow.exception.NotFoundException;
import hei.school.carshow.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/Users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*@GetMapping("/User/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id).orElseThrow(NotFoundException::new);
    }*/

    @PostMapping("/User")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/admin/User/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updateUser) {
        User updatedUser = userService.updateUser(id, updateUser);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/User/Delete/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId).orElseThrow(NotFoundException::new);
    }

}
