package hei.school.carshow.controller;

import hei.school.carshow.db.entity.Car;
import hei.school.carshow.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCar();
    }

    @GetMapping("/cars/{limit}")
    public List<String> getSixCar(@PathVariable int limit) {
        return carService.getDistinctBrandsLimited(limit);
    }

    @GetMapping("/cars/model")
    public List<String> getModel() {
        return carService.getDistinctModel();
    }

    @GetMapping("/cars/typeModelPrice")
    public List<String> getTypeModelPrice() {
        return carService.getTypeModelPrice();
    }

    @GetMapping("/cars/type")
    public List<String> getTypeByPrice() {
        return carService.getType();
    }

    @GetMapping("/car/minPrice")
    public double getMinPrice() {
        return carService.getMinPrice();
    }

    @GetMapping("/car/maxPrice")
    public double getMaxPrice() {
        return carService.getMaxPrice();
    }

    @GetMapping("/cars/motorType")
    public List<String> getMotorType() {
        return carService.getMotorType();
    }

    /*
    @GetMapping("/cars/Info")
    public List<String> getInfoCar() {
        return carService.getCarInfo();
    }*/

    @GetMapping("/admin/Car/{id}")
    public Optional<Car> getCarById(@PathVariable String id) {
        return carService.getCarById(id);
    }

    @PostMapping("/admin/Car")
    public Car newCar(@RequestBody Car car) {
        return carService.newCar(car);
    }

    @PutMapping("/admin/car/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable String id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        if (updatedCar != null) {
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/car/delete/{id}")
    public void deleteCarById(@PathVariable String id) {
        carService.deleteCarById(id);
    }
}
