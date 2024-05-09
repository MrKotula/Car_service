package ua.foxminded.carservice.service;

import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.dto.CarDto;
import java.util.List;

public interface CarService {
    CarDto getCarById(String carId);

    void saveAll(List<Car> listOfCars);

    List<CarDto> getCarsByBrand(String brand);

    List<CarDto> getCarsByModel(String model);

    List<CarDto> getCarsByYear(int year);

    List<CarDto> getCarsByCategory(String category);
}
