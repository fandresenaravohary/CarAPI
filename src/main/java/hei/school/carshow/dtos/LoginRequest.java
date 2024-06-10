package hei.school.carshow.dtos;

import lombok.Data;

public record LoginRequest (
     String email,
     String password
){}
