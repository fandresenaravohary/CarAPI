package hei.school.carshow.config;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import hei.school.carshow.db.entity.User;
import hei.school.carshow.service.FirebaseService;
import hei.school.carshow.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class FirebaseAuthFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private final FirebaseService firebaseService;
    private final UserService userService;
    private final RequestMatcher filteredMatcher;

    public static String getFirebaseToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
            //TODO: Handle to show cool exception
            throw new RuntimeException();
        }
        return bearerToken.substring(BEARER_PREFIX.length());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        String token = FirebaseAuthFilter.getFirebaseToken(request);
        try {
            FirebaseToken firebaseToken =  firebaseService.getFirebaseUserByToken(token);
            Optional<User> user = userService.getUserById(UUID.fromString(firebaseToken.getUid()));
            FirebaseAuthentication authentication = new FirebaseAuthentication(
                    user.orElse(User.builder()
                            .id_user(UUID.fromString(firebaseToken.getUid()))
                            .email(firebaseToken.getEmail())
                            .name(firebaseToken.getName() == null ? firebaseToken.getEmail() : firebaseToken.getName())
                            .build()
                    ),
                    token,
                    user.isPresent()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return filteredMatcher.matches(request);
    }
}