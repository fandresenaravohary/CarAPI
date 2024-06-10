package hei.school.carshow.service.impl;

import hei.school.carshow.entity.Appointment;
import hei.school.carshow.entity.User;
import hei.school.carshow.enums.Status;
import hei.school.carshow.repository.AppointmentRepository;
import hei.school.carshow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) {
        appointment.setStatus(Status.pending);

        Optional<User> optionalUser = userRepository.findByEmail(appointment.getEmail());
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + appointment.getEmail()));

        appointment.setUser(user);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointement() {
        return appointmentRepository.findAll();
    }

    public Appointment updateAppointmentStatus(String appointmentId, Status newStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + appointmentId));

        Status oldStatus = appointment.getStatus();
        appointment.setStatus(newStatus);

        if (!oldStatus.equals(newStatus) && (oldStatus.equals(Status.confirm) || oldStatus.equals(Status.rejected))) {
            User user = appointment.getUser();
            if (user != null) {
                sendEmail(appointment.getEmail(), newStatus);
            }
        }

        return appointmentRepository.save(appointment);
    }

    private void sendEmail(String recipientEmail, Status newStatus) {

    }
}
