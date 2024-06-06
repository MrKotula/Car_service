package ua.foxminded.carservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.carservice.dto.CarDto;
import ua.foxminded.carservice.service.CarService;
import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
@Tag(name = "Car Management", description = "Operations related to managing cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    @PreAuthorize("hasAnyRole('app_admin', 'app_user')")
    @Operation(summary = "Get all cars", description = "Returns a list of all cars",
            security = @SecurityRequirement(name = "my_oAuth_security_schema"))
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.getAllCars();

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{carId}")
    @PreAuthorize("hasAnyRole('app_admin', 'app_user')")
    @Operation(summary = "Get car by id", description = "Returns a car by its carId",
            security = @SecurityRequirement(name = "my_oAuth_security_schema"))
    public ResponseEntity<CarDto> getCarById(@Parameter(description = "carId of the car to be retrieved") @PathVariable String carId) {
        CarDto car = carService.getCarById(carId);

        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('app_admin')")
    @Operation(summary = "Save new cars", description = "Saves a list of new cars",
            security = @SecurityRequirement(name = "my_oAuth_security_schema"))
    public ResponseEntity<List<CarDto>> saveNewCars(@Parameter(description = "List of cars to be saved") @RequestBody List<CarDto> cars) {
        List<CarDto> savedCars = carService.getAllCars();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCars);
    }

    @PutMapping("/{carId}")
    @PreAuthorize("hasRole('app_admin')")
    @Operation(summary = "Update car", description = "Updates an existing car",
            security = @SecurityRequirement(name = "my_oAuth_security_schema"))
    public void updateCar(@Parameter(description = "id of the car to be updated") @PathVariable String carId,
                          @Parameter(description = "Updated car object") @RequestBody CarDto carDto) {
        carService.updateCar(carDto);
    }

    @DeleteMapping("/{carId}")
    @PreAuthorize("hasRole('app_admin')")
    @Operation(summary = "Delete car by carId", description = "Deletes a car by its carId",
            security = @SecurityRequirement(name = "my_oAuth_security_schema"))
    public void deleteCarById(@Parameter(description = "ID of the car to be deleted") @PathVariable String carId) {
        carService.deleteCarById(carId);
    }
}
