package LapTrinhWeb.Thymeleaf.repository;

import LapTrinhWeb.Thymeleaf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
