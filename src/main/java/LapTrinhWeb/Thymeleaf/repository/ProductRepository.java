package LapTrinhWeb.Thymeleaf.repository;

import LapTrinhWeb.Thymeleaf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findDistinctByCategoriesId(Long categoryId);
}
