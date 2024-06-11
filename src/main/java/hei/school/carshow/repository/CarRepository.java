package hei.school.carshow.repository;

import hei.school.carshow.dtos.CarInfoDTO;
import hei.school.carshow.dtos.CarTypePriceDTO;
import hei.school.carshow.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    @Query("select distinct c.brand from Car c")
    List<String> findDistinctByBrand(Pageable pageable);

    @Query("select distinct c.model from Car c")
    List<String> findDistinctByModel();

    @Query("select new hei.school.carshow.dtos.CarTypePriceDTO(c.motorType, c.type, c.price) from Car c order by c.price asc")
    List<CarTypePriceDTO> findTypeModelPrice();

    @Query("select c.type from Car c")
    List<String> findDistinctByType();

    @Query("select MIN(c.price) from Car c")
    Double findMin();

    @Query("select MAX(c.price) from Car c")
    Double findMax();

    @Query("select distinct c.motorType from Car c")
    List<String> findDistinctByMotorType();


    @Query("select new hei.school.carshow.dtos.CarInfoDTO(c.brand, c.color, c.description, c.model, c.motorType, c.name, c.power, c.price, c.type, i.url) " +
            "from Image i inner join i.car c")
    List<CarInfoDTO> findCarInfo();
}
