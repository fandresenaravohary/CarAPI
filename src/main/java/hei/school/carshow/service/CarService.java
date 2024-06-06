package hei.school.carshow.service;

import hei.school.carshow.db.entity.Car;
import hei.school.carshow.db.entity.Image;
import hei.school.carshow.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Car newCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> getCarById(String id) {
        return carRepository.findById(id);
    }

    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    public Car updateCar(String id, Car updatedCar) {
        Optional<Car> existingCar = carRepository.findById(id);

        if (existingCar.isPresent()) {
            Car carToUpdate = existingCar.get();

            carToUpdate.setName(updatedCar.getName());
            carToUpdate.setDescription(updatedCar.getDescription());
            carToUpdate.setBrand(updatedCar.getBrand());
            carToUpdate.setModel(updatedCar.getModel());
            carToUpdate.setPrice(updatedCar.getPrice());
            carToUpdate.setColor(updatedCar.getColor());
            carToUpdate.setMotor_type(updatedCar.getMotor_type());
            carToUpdate.setPower(updatedCar.getPower());
            carToUpdate.setPlace_number(updatedCar.getPlace_number());
            carToUpdate.setStatus(updatedCar.getStatus());
            carToUpdate.setType(updatedCar.getType());

            Image image = new Image();
            carToUpdate.setImage(image);

            return carRepository.save(carToUpdate);
        } else {
            throw new IllegalArgumentException("Invalid ID " + id);
        }
    }

    public void deleteCarById(String id) {
        carRepository.deleteById(id);
    }

    public List<String> getDistinctBrandsLimited(int limit) {
        return carRepository.findDistinctByBrand((Pageable) PageRequest.of(0, limit));
    }

    public List<String> getDistinctModel() {
        return carRepository.findDistinctByModel();
    }

    public List<String> getTypeModelPrice() {
        return carRepository.findTypeModelPrice();
    }

    public List<String> getType() {
        return carRepository.findDistinctByType();
    }

    public double getMinPrice() {
        return carRepository.findMin();
    }

    public double getMaxPrice() {
        return carRepository.findMax();
    }

    public List<String> getMotorType() {
        return carRepository.findDistinctByMotor_type();
    }

    /*public List<String> getCarInfo() {
        return carRepository.findCarInfo();
    }*/
}
