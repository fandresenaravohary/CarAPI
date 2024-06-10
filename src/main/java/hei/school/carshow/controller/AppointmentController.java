package hei.school.carshow.controller;

import hei.school.carshow.entity.Appointment;
import hei.school.carshow.service.impl.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/admin/appointments")
    public List<Appointment> getAllAppointment() {
        return appointmentService.getAllAppointement();
    }

    @PostMapping("/newAppointment")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }
}
