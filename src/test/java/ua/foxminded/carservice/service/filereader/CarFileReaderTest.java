package ua.foxminded.carservice.service.filereader;

import org.junit.jupiter.api.Test;
import ua.foxminded.carservice.data.entity.Car;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarFileReaderTest {

    @Test
    void shouldReadCarsFromFileTest() throws IOException {
        String testData = "ZRgPP9dBMm,Audi,2020,Q3,SUV\n" +
                "cptB1C1NSL,Chevrolet,2020,Malibu,Sedan";

        Path tempFile = Files.createTempFile("test-cars", ".csv");
        Files.writeString(tempFile, testData);

        CarFileReader carFileReader = new CarFileReader();
        List<Car> cars = carFileReader.readCarsFromFile(tempFile.toString());

        assertEquals(2, cars.size());

        assertEquals("ZRgPP9dBMm", cars.get(0).getCarId());
        assertEquals("Audi", cars.get(0).getBrand());
        assertEquals(2020, cars.get(0).getYear());
        assertEquals("Q3", cars.get(0).getModel());
        assertEquals("SUV", cars.get(0).getCategory());

        assertEquals("cptB1C1NSL", cars.get(1).getCarId());
        assertEquals("Chevrolet", cars.get(1).getBrand());
        assertEquals(2020, cars.get(1).getYear());
        assertEquals("Malibu", cars.get(1).getModel());
        assertEquals("Sedan", cars.get(1).getCategory());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldNotReadCarsFromFileTest() throws IOException {
        String testData = "ZRgPP9dBMm,Audi,2020,Q3,SUV, BadParam\n" +
                "cptB1C1NSL,Chevrolet,2020,Malibu,Sedan, BadParam";

        Path tempFile = Files.createTempFile("test-cars", ".csv");
        Files.writeString(tempFile, testData);

        CarFileReader carFileReader = new CarFileReader();

        assertDoesNotThrow(() -> carFileReader.readCarsFromFile(tempFile.toString()));
    }
}
