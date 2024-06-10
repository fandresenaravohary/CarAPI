package hei.school.carshow.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String carId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private double price;

    @Column(name = "color")
    private String color;

    @Column(name = "motor_type")
    private String motorType;

    @Column(name = "power", columnDefinition = "TEXT")
    private String power;

    @Column(name = "place_number", columnDefinition = "INT")
    private int placeNumber;

    @Column(name = "status")
    private String Status;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_image", nullable = false)
    private Image image;

}
