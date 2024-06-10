package hei.school.carshow.controller;

import hei.school.carshow.dtos.CarInfoDTO;
import hei.school.carshow.dtos.CarTypePriceDTO;
import hei.school.carshow.entity.Car;
import hei.school.carshow.service.impl.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCar();
    }

    @GetMapping("/cars/{limit}")
    public List<String> getSixCar(@PathVariable Integer limit) {
        return carService.getDistinctBrandsLimited(limit);
    }

    @GetMapping("/cars/model")
    public List<String> getModel() {
        return carService.getDistinctModel();
    }

    @GetMapping("/cars/typeModelPrice")
    public List<CarTypePriceDTO> getTypeModelPrice() {
        return carService.getTypeModelPrice();
    }

    @GetMapping("/cars/type")
    public List<String> getTypeByPrice() {
        return carService.getType();
    }

    @GetMapping("/cars/minPrice")
    public double getMinPrice() {
        return carService.getMinPrice();
    }

    @GetMapping("/cars/maxPrice")
    public double getMaxPrice() {
        return carService.getMaxPrice();
    }

    @GetMapping("/cars/motorType")
    public List<String> getMotorType() {
        return carService.getMotorType();
    }

    @GetMapping("/cars/Info")
    public List<CarInfoDTO> getInfoCar() {
        return carService.getCarInfo();
    }

    @GetMapping("/admin/cars/{id}")
    public Optional<Car> getCarById(@PathVariable UUID id) {
        return carService.getCarById(id);
    }

    @PostMapping("/admin/cars")
    public Car newCar(@RequestBody Car car) {
        return carService.newCar(car);
    }

    @PutMapping("/admin/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable UUID id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        if (updatedCar != null) {
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/cars/delete/{id}")
    public void deleteCarById(@PathVariable UUID id) {
        carService.deleteCarById(id);
    }
}
