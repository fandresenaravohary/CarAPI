package hei.school.carshow.service;


import hei.school.carshow.dtos.AuthResponse;
import hei.school.carshow.dtos.LoginRequest;
import hei.school.carshow.dtos.UserRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse register(UserRequest userRequest);
}
