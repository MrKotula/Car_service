package ua.foxminded.carservice.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.carservice.data.entity.Car;
import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findCarByBrand(String brand);

    List<Car> findCarByModel(String model);

    List<Car> findCarByYear(int year);

    List<Car> findCarByCategory(String category);
}
