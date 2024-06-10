package hei.school.carshow.dtos;


public record UserRequest(
        String id,

        String email,

        String name,

        String password
) {
}
