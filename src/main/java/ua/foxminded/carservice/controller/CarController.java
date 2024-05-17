package ua.foxminded.carservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.carservice.dto.CarDto;
import ua.foxminded.carservice.service.CarService;
import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.getAllCars();

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable String carId) {
        CarDto car = carService.getCarById(carId);

        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<List<CarDto>> saveNewCars(@RequestBody List<CarDto> cars) {
        List<CarDto> savedCars = carService.getAllCars();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCars);
    }

    @PutMapping("/{carId}")
    public void updateCar(@PathVariable String carId, @RequestBody CarDto carDto) {
        carService.updateCar(carDto);
    }

    @DeleteMapping("/{carId}")
    public void deleteCarById(@PathVariable String carId) {
        carService.deleteCarById(carId);
    }
}
