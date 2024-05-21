package hei.school.carshow.controller;

import hei.school.carshow.db.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PingController {
    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }
}
