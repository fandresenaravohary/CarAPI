package hei.school.carshow.config;

import hei.school.carshow.service.FirebaseService;
import hei.school.carshow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConf {
    private final FirebaseService firebaseService;
    private final UserService userService;
    private final AuthProvider authProvider;

    public FirebaseAuthFilter configureFilter(RequestMatcher matcher){
        return new FirebaseAuthFilter(firebaseService, userService, matcher);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource( request -> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.addAllowedHeader("*");
                            configuration.addAllowedMethod("*");
                            configuration.addAllowedOrigin("*");
                            return configuration;
                        })
                )
                // not authenticated
                .addFilterBefore(configureFilter(
                                new OrRequestMatcher(
                                        new AntPathRequestMatcher("/ping"),
                                        new AntPathRequestMatcher("/dummy-table"),
                                        new AntPathRequestMatcher("/users/*"),
                                        new AntPathRequestMatcher("/cars"),
                                        new AntPathRequestMatcher("/Images"),
                                        new AntPathRequestMatcher("/cars/model"),
                                        new AntPathRequestMatcher("/cars/type"),
                                        new AntPathRequestMatcher("/cars/typeModelPrice"),
                                        new AntPathRequestMatcher("/cars/Info")
                                )),
                        UsernamePasswordAuthenticationFilter.class
                )
                .authenticationProvider(authProvider)
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/ping")
                        .permitAll()
                        .requestMatchers("/dummy-table")
                        .permitAll()
                        .requestMatchers("/users/*")
                        .permitAll()
                        .requestMatchers("/cars")
                        .permitAll()
                        .requestMatchers("/Images")
                        .permitAll()
                        .requestMatchers("/cars/model")
                        .permitAll()
                        .requestMatchers("/cars/type")
                        .permitAll()
                        .requestMatchers("/cars/typeModelPrice")
                        .permitAll()
                        .requestMatchers("/cars/Info")
                        .permitAll()

                        // authenticated
                        .requestMatchers("/whoami")
                        .authenticated()
                        .requestMatchers("/admin/**")
                        .authenticated()
                        
                );
        return http.build();
    }
}
