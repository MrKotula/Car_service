package ua.foxminded.carservice.service.startup;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.service.CarService;
import ua.foxminded.carservice.service.filereader.CarFileReader;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class StartAppImpl implements StartApp {

    private static final String FILE_PATH = "classpath:file.csv";

    private final CarFileReader carFileReader;
    private final CarService carService;
    private final ResourceLoader resourceLoader;

    @Override
    public void startApplication() throws IOException {
        updateDbData();
    }

    private void updateDbData() throws IOException {
        Resource resource = resourceLoader.getResource(FILE_PATH);
        List<Car> listOfCars = carFileReader.readCarsFromFile(resource.getFile().getPath());

        carService.saveAll(listOfCars);
    }
}
