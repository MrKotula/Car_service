package ua.foxminded.carservice.service.startup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.service.filereader.CarFileReader;
import ua.foxminded.carservice.service.impl.CarServiceImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartAppImplTest {

    @Mock
    private CarFileReader carFileReader;

    @Mock
    private CarServiceImpl carService;

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private StartAppImpl startApp;

    @Test
    void verifyUpdateDbDataTest() throws IOException {
        List<Car> listOfCars = new ArrayList<>();

        Resource resource = mock(Resource.class);

        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getFile()).thenReturn(new File("classpath:file.csv"));
        when(carFileReader.readCarsFromFile(anyString())).thenReturn(listOfCars);

        startApp.startApplication();

        verify(carFileReader).readCarsFromFile(anyString());
        verify(carService).saveAll(listOfCars);
    }
}
