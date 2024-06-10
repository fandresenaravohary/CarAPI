package hei.school.carshow.service.impl;


import hei.school.carshow.dtos.AuthResponse;
import hei.school.carshow.dtos.LoginRequest;
import hei.school.carshow.dtos.UserRequest;
import hei.school.carshow.enums.Role;
import hei.school.carshow.exception.BadRequestException;
import hei.school.carshow.mappers.UserMapper;
import hei.school.carshow.repository.UserRepository;
import hei.school.carshow.service.AuthService;
import hei.school.carshow.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse login(LoginRequest request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        var user = this.userRepository.findByEmail(request.email()).orElseThrow(() -> new UsernameNotFoundException("Invalid Email"));
        var token = this.jwtService.generateJwtToken(user);
        return new AuthResponse("Bearer", token);
    }

    @Transactional
    @Override
    public AuthResponse register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new BadRequestException("Email already exists");
        }

        final var user = userMapper.toEntity(userRequest);
        var pwd = user.getPassword();
        if (pwd != null) {
            user.setPassword(passwordEncoder.encode(pwd));
        }
        user.setRole(Role.ADMIN);
        final var userCreated = userRepository.save(user);
        final var token = jwtService.generateJwtToken(userCreated);
        userCreated.setToken(token);
        userRepository.save(userCreated);
        return new AuthResponse("Bearer", token);
    }

}
