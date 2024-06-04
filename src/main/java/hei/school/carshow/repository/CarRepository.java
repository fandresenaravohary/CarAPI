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
}
