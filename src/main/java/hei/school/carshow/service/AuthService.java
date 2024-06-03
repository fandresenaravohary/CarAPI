package hei.school.carshow.service;

import hei.school.carshow.config.FirebaseAuthentication;
import hei.school.carshow.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    public static FirebaseAuthentication getAuthentication(){
        return (FirebaseAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }


    public User getWhoAmi(){
        FirebaseAuthentication authentication = getAuthentication();
        if(!authentication.isRegistered()){
            return userService.saveOrUpdate(authentication.getUser());
        }
        return authentication.getUser();
    }
}
