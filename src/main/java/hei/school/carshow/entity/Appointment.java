package hei.school.carshow.entity;

import hei.school.carshow.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String appointmentId;

    private String name;

    private String firstName;

    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "contact")
    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Instant appointmentDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;
}
