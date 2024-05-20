package hei.school.carshow.db.entity;

import hei.school.carshow.db.entity.enumm.Status;
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
    @Column(name = "id_appointment", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id_appointment;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "email")
    private String email;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "contact")
    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Instant appointment_date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
