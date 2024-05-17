package ua.foxminded.carservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.data.repository.CarRepository;
import ua.foxminded.carservice.service.CarService;
import ua.foxminded.carservice.dto.CarDto;
import ua.foxminded.carservice.service.mapper.CarMapper;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarDto getCarById(String carId) {
        Car car = carRepository.findById(carId).orElse(null);

        return carMapper.transformCarToDto(car);
    }

    @Override
    public void saveAll(List<Car> listOfCars) {
        carRepository.saveAll(listOfCars);
    }

    @Override
    public List<CarDto> getCarsByBrand(String brand) {
        List<Car> listOfCars = carRepository.findCarByBrand(brand);

        return carMapper.transformCarListToCarDtoList(listOfCars);
    }

    @Override
    public List<CarDto> getCarsByModel(String model) {
        List<Car> listOfCars = carRepository.findCarByModel(model);

        return carMapper.transformCarListToCarDtoList(listOfCars);
    }

    @Override
    public List<CarDto> getCarsByYear(int year) {
        List<Car> listOfCars = carRepository.findCarByYear(year);

        return carMapper.transformCarListToCarDtoList(listOfCars);
    }

    @Override
    public List<CarDto> getCarsByCategory(String category) {
        List<Car> listOfCars = carRepository.findCarByCategory(category);

        return carMapper.transformCarListToCarDtoList(listOfCars);
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> listOfCars = carRepository.findAll();

        return carMapper.transformCarListToCarDtoList(listOfCars);
    }

    @Override
    public void updateCar(CarDto carDto) {
        Car updatedCar = Car.builder()
                .carId(carDto.getCarId())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .year(carDto.getYear())
                .category(carDto.getCategory())
                .build();

        carRepository.save(updatedCar);
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.deleteById(carId);
    }
}
