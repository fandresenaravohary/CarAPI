package hei.school.carshow.controller;

import hei.school.carshow.db.entity.User;
import hei.school.carshow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/whoami")
    public User whoami(){
        return authService.getWhoAmi();
    }
}