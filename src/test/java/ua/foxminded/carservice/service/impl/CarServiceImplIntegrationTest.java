package ua.foxminded.carservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.dto.CarDto;
import ua.foxminded.carservice.service.CarService;
import ua.foxminded.carservice.service.startup.StartApp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class CarServiceImplIntegrationTest {

    @Autowired
    private StartApp startApp;

    @Autowired
    private CarService carService;

    @Container
    private static final GenericContainer<?> mongoContainer = new GenericContainer<>("mongo:latest")
            .withExposedPorts(27017);

    @BeforeEach
    void setUp() throws IOException {
        startApp.startApplication();
    }

    @Test
    void shouldReturnCarDtoWhenUseMethodGetCarByIdTest() {
        CarDto testCarDto = new CarDto("ZRgPP9dBMm", "Audi", "Q3", 2020, "SUV");

        assertEquals(testCarDto, carService.getCarById("ZRgPP9dBMm"));
    }

    @Test
    void shouldSaveAllWhenUseMethodSaveAllTest() {
        List<Car> listOfCars = new ArrayList<>();

        Car testCarVW = new Car("1S3a", "VOLKSWAGEN", "Jetta", 2006, "Sedan");
        Car testCarBMW = new Car("21sW", "BMW", "M3", 2012, "Sedan");

        CarDto testCarDtoVW = new CarDto("1S3a", "VOLKSWAGEN", "Jetta", 2006, "Sedan");
        CarDto testCarDtoBMW = new CarDto("21sW", "BMW", "M3", 2012, "Sedan");

        listOfCars.add(testCarVW);
        listOfCars.add(testCarBMW);

        carService.saveAll(listOfCars);

        assertEquals(testCarDtoVW, carService.getCarById("1S3a"));
        assertEquals(testCarDtoBMW, carService.getCarById("21sW"));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByBrandTest() {
        List<CarDto> listOfCarsDto = new ArrayList<>();

        CarDto testCarBMW = new CarDto("7G1VT2pSNO", "BMW", "3 Series", 2020, "Sedan");

        listOfCarsDto.add(testCarBMW);

        assertEquals(listOfCarsDto.get(0), carService.getCarsByBrand("BMW").get(0));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByModelTest() {
        List<CarDto> listOfCarsDto = new ArrayList<>();

        CarDto testCarBMW = new CarDto("7G1VT2pSNO", "BMW", "3 Series", 2020, "Sedan");

        listOfCarsDto.add(testCarBMW);

        assertEquals(listOfCarsDto.get(0), carService.getCarsByModel("3 Series").get(0));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByYearTest() {
        List<CarDto> listOfCarsDto = new ArrayList<>();

        CarDto testCarAudi = new CarDto("ZRgPP9dBMm", "Audi", "Q3", 2020, "SUV");

        listOfCarsDto.add(testCarAudi);

        assertEquals(listOfCarsDto.get(0), carService.getCarsByYear(2020).get(0));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetCarsByCategoryTest() {
        List<CarDto> listOfCarsDto = new ArrayList<>();

        CarDto testCarAudi = new CarDto("ZRgPP9dBMm", "Audi", "Q3", 2020, "SUV");

        listOfCarsDto.add(testCarAudi);

        assertEquals(listOfCarsDto.get(0), carService.getCarsByCategory("SUV").get(0));
    }

    @Test
    void shouldReturnListCarDtoWhenUseMethodGetAllCarsTest() {
        List<CarDto> listOfCarsDto = new ArrayList<>();

        CarDto testCarAudi = new CarDto("ZRgPP9dBMm", "Audi", "Q3", 2020, "SUV");

        listOfCarsDto.add(testCarAudi);

        assertEquals(listOfCarsDto.get(0), carService.getAllCars().get(0));
    }

    @Test
    void shouldUpdateCarDtoWhenUseMethodUpdateCarTest() {
        CarDto testCarAudi = new CarDto("ZRgPP9dBMm", "VOLKSWAGEN", "Arteon", 2020, "Sedan");

        carService.updateCar(testCarAudi);

        assertEquals(testCarAudi, carService.getCarById("ZRgPP9dBMm"));
    }
}
