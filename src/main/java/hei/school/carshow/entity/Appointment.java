package hei.school.carshow.entity;

import hei.school.carshow.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@Builder
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String firstName;

    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    private String contact;

    private Instant appointmentDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;
}
