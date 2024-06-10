package hei.school.carshow.dtos;


import java.util.UUID;

public record UserRequest(
        UUID id,

        String email,

        String name,

        String password
) {
}
