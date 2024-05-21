package hei.school.carshow.service;

import hei.school.carshow.db.entity.LoginRequest;
import hei.school.carshow.db.entity.User;
import hei.school.carshow.db.entity.enumm.Role;
import hei.school.carshow.repository.UserRepository;
import hei.school.carshow.service.configuration.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (request.getPassword().equals(user.getPassword())) {
                return jwtService.generateToken(user);
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @SuppressWarnings("null")
    public User getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public User createUser(User user) {
        user.setRole(Role.CLIENT);
        User save = userRepository.save(user);
        new InMemoryUserDetailsManager(save);
        return save;
    }

    @SuppressWarnings("null")
    public User updateUser(UUID id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setEmail(updatedUser.getEmail());

            return userRepository.save(userToUpdate);
        } else {
            return null;
        }
    }

    @SuppressWarnings("null")
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
