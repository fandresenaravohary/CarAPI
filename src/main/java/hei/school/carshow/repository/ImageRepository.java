package hei.school.carshow.repository;

import hei.school.carshow.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ImageRepository extends JpaRepository<Image, String> {
}
