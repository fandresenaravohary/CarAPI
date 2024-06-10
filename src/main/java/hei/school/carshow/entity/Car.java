package hei.school.carshow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@Builder
public class Car {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID carId;

    private String name;

    @Column( columnDefinition = "TEXT")
    private String description;

    private String brand;

    private String model;

    private Double price;

    private String color;

    private String motorType;

    private String power;

    private Integer placeNumber;

    private String Status;

    private String type;

    @ManyToOne
    private Image image;

}
