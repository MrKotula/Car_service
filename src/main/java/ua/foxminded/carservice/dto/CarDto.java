package ua.foxminded.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String carId;
    private String brand;
    private String model;
    private int year;
    private String category;
}
