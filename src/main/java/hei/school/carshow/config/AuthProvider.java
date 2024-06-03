package hei.school.carshow.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //TODO: maybe do the authentication here
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FirebaseAuthentication.class.isAssignableFrom(authentication);
    }
}
