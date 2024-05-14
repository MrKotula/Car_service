package ua.foxminded.carservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.data.repository.CarRepository;
import ua.foxminded.carservice.dto.CarDto;
import ua.foxminded.carservice.service.mapper.CarMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void shouldReturnCarDtoWhenUseMethodGetCarByIdTest() {
        Car testCar = new Car("1S3a", "VOLKSWAGEN", "Jetta", 2006, "Sedan");
        CarDto testCarDto = new CarDto("1S3a", "VOLKSWAGEN", "Jetta", 2006, "Sedan");

        when(carRepository.findById("1s3a")).thenReturn(Optional.of(testCar));
        when(carMapper.transformCarToDto(testCar)).thenReturn(testCarDto);

        assertEquals(testCarDto, carService.getCarById("1s3a"));
    }

    @Test
    void shouldSaveAllWhenUseMethodSaveAllTest() {
        List<Car> listOfCars = new ArrayList<>();

        Car testCarVW = new Car("1S3a", "VOLKSWAGEN", "Jetta", 2006, "Sedan");
        Car testCarBMW = new Car("21sW", "BMW", "M3", 2012, "Sedan");

        listOfCars.add(testCarVW);
        listOfCars.add(testCarBMW);

        carService.saveAll(listOfCars);

        verify(carRepository).saveAll(listOfCars);
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByBrandTest() {
        List<Car> listOfCars = new ArrayList<>();
        List<CarDto> listOfCarsDto = new ArrayList<>();

        Car testCarBMW = new Car("21sW", "BMW", "M3", 2012, "Sedan");
        CarDto testCarDtoBMW = new CarDto("21sW", "BMW", "M3", 2012, "Sedan");

        listOfCars.add(testCarBMW);
        listOfCarsDto.add(testCarDtoBMW);

        when(carRepository.findCarByBrand("BMW")).thenReturn(listOfCars);
        when(carMapper.transformCarListToCarDtoList(listOfCars)).thenReturn(listOfCarsDto);

        assertEquals(listOfCarsDto, carService.getCarsByBrand("BMW"));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByModelTest() {
        List<Car> listOfCars = new ArrayList<>();
        List<CarDto> listOfCarsDto = new ArrayList<>();

        Car testCarBMW = new Car("21sW", "BMW", "M3", 2012, "Sedan");
        CarDto testCarDtoBMW = new CarDto("21sW", "BMW", "M3", 2012, "Sedan");

        listOfCars.add(testCarBMW);
        listOfCarsDto.add(testCarDtoBMW);

        when(carRepository.findCarByModel("M3")).thenReturn(listOfCars);
        when(carMapper.transformCarListToCarDtoList(listOfCars)).thenReturn(listOfCarsDto);

        assertEquals(listOfCarsDto, carService.getCarsByModel("M3"));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByYearTest() {
        List<Car> listOfCars = new ArrayList<>();
        List<CarDto> listOfCarsDto = new ArrayList<>();

        Car testCarBMW = new Car("21sW", "BMW", "M3", 2012, "Sedan");
        CarDto testCarDtoBMW = new CarDto("21sW", "BMW", "M3", 2012, "Sedan");

        listOfCars.add(testCarBMW);
        listOfCarsDto.add(testCarDtoBMW);

        when(carRepository.findCarByYear(2012)).thenReturn(listOfCars);
        when(carMapper.transformCarListToCarDtoList(listOfCars)).thenReturn(listOfCarsDto);

        assertEquals(listOfCarsDto, carService.getCarsByYear(2012));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByCategoryTest() {
        List<Car> listOfCars = new ArrayList<>();
        List<CarDto> listOfCarsDto = new ArrayList<>();

        Car testCarBMW = new Car("21sW", "BMW", "M3", 2012, "Sedan");
        CarDto testCarDtoBMW = new CarDto("21sW", "BMW", "M3", 2012, "Sedan");

        listOfCars.add(testCarBMW);
        listOfCarsDto.add(testCarDtoBMW);

        when(carRepository.findCarByCategory("Sedan")).thenReturn(listOfCars);
        when(carMapper.transformCarListToCarDtoList(listOfCars)).thenReturn(listOfCarsDto);

        assertEquals(listOfCarsDto, carService.getCarsByCategory("Sedan"));
    }

    @Test
    void shouldDeleteCarWhenUseMethodDeleteCarByIdTest() {
        carService.deleteCarById("cptB1C1NSL");

        verify(carRepository).deleteById("cptB1C1NSL");
    }
}
