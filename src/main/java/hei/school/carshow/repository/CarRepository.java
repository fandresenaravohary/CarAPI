package hei.school.carshow.repository;

import hei.school.carshow.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface CarRepository extends JpaRepository<Car, String> {
    @Query("select distinct c.brand from Car c")
    List<String> findDistinctByBrand(Pageable pageable);

    @Query("select distinct c.model from Car c")
    List<String> findDistinctByModel();

    @Query("select c.motor_type, c.type, c.price from Car c order by c.price asc")
    List<String> findTypeModelPrice();

    @Query("select c.type from Car c")
    List<String> findDistinctByType();

    @Query("select MIN(c.price) from Car c")
    double findMin();

    @Query("select MAX(c.price) from Car c")
    double findMax();

    @Query("select distinct c.motor_type from Car c")
    List<String> findDistinctByMotor_type();

    /*@Query("select c.brand, c.color, c.description, c.model, c.motor_type, c.name, c.place_number, c.power, c.price, c.type, i.url from Car c inner join Image i on i.id_image = c.image")
    List<String> findCarInfo();*/
}
