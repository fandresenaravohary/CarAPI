package hei.school.carshow.mappers;

import hei.school.carshow.dtos.UserRequest;
import hei.school.carshow.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest userRequest) {
        return (new User())
                .setEmail(userRequest.email())
                .setPassword(userRequest.password())
                .setName(userRequest.name())
                .setUserId(userRequest.id());
    }

}
