package ua.foxminded.carservice.service.filereader;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.carservice.data.entity.Car;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CarFileReader {
    private static final String LINE_SEPARATOR = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private static final int MAX_VALUE = 5;
    private static final int CAR_ID_VALUE = 0;
    private static final int BRAND_VALUE = 1;
    private static final int YEAR_VALUE = 2;
    private static final int MODEL_VALUE = 3;
    private static final int CATEGORY_VALUE = 4;

    public List<Car> readCarsFromFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(line -> line.split(LINE_SEPARATOR))
                .filter(parts -> parts.length == MAX_VALUE)
                .map(parts -> {
                    String carId = parts[CAR_ID_VALUE].trim();
                    String brand = parts[BRAND_VALUE].trim();
                    int year = Integer.parseInt(parts[YEAR_VALUE].trim());
                    String model = parts[MODEL_VALUE].trim();
                    String category = parts[CATEGORY_VALUE].trim();

                    return new Car(carId, brand, model, year, category);
                })
                .collect(Collectors.toList());
    }
}
