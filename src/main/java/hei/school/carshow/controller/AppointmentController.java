package hei.school.carshow.controller;

import hei.school.carshow.db.entity.Appointment;
import hei.school.carshow.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/admin/appointments")
    public List<Appointment> getAllAppointment() {
        return appointmentService.getAllAppointement();
    }

    @PostMapping("/newAppointment")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }
}
