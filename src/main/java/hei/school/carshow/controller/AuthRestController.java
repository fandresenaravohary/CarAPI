package hei.school.carshow.controller;


import hei.school.carshow.dtos.AuthResponse;
import hei.school.carshow.dtos.LoginRequest;
import hei.school.carshow.dtos.UserRequest;
import hei.school.carshow.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users")
public class AuthRestController {
    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping
    public AuthResponse register(@RequestBody UserRequest userRequest) {
        return authService.register(userRequest);
    }
}
