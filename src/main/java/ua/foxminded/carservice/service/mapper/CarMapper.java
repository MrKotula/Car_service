package ua.foxminded.carservice.service.mapper;

import org.mapstruct.Mapper;
import ua.foxminded.carservice.data.entity.Car;
import ua.foxminded.carservice.dto.CarDto;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto transformCarToDto(Car car);

    Car transformCarDtoToCar(CarDto carDto);

    List<CarDto> transformCarListToCarDtoList(List<Car> carList);
}
