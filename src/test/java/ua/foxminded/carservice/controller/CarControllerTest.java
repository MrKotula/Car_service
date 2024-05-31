package ua.foxminded.carservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.carservice.dto.CarDto;
import ua.foxminded.carservice.service.CarService;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Mock
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities = "ROLE_app_user")
    void shouldReturnCarDtoWhenCarExists() throws Exception {
        CarDto expectedCarDto = new CarDto("123", "Toyota", "Camry", 2022, "Sedan");

        when(carService.getCarById("123")).thenReturn(expectedCarDto);

        mockMvc.perform(get("/cars/123")
                        .param("carId", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_app_user")
    void shouldReturnNotFoundWhenCarDoesNotExist() throws Exception {
        when(carService.getCarById("456")).thenReturn(null);

        mockMvc.perform(get("/cars/456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(authorities = "ROLE_app_user")
    void shouldReturnListCarDtoWhenUseGetAllCarsTest() throws Exception {
        mockMvc.perform(get("/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_app_admin")
    void shouldSaveListCarsWhenUseSaveAllTest() throws Exception {
        List<CarDto> cars = new ArrayList<>();

        cars.add(new CarDto("1", "Toyota", "Corolla", 2020, "Sedan"));
        cars.add(new CarDto("2", "Honda", "Civic", 2021, "Sedan"));

        when(carService.getAllCars()).thenReturn(cars);

        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cars)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = "ROLE_app_admin")
    void shouldUpdateCarWhenUseMethodUpdateCarTest() throws Exception {
        CarDto carDto = new CarDto("123", "Audi", "Q3", 2020, "SUV");

        when(carService.getCarById("123")).thenReturn(carDto);

        mockMvc.perform(put("/cars/123")
                        .param("carId", "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_app_admin")
    void shouldDeleteCarWhenUseMethodDeleteCarByIdTest() throws Exception {
        mockMvc.perform(delete("/cars/123")
                        .param("carId", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
