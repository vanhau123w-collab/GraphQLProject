package LapTrinhWeb.Thymeleaf.service;

import LapTrinhWeb.Thymeleaf.model.Category;
import LapTrinhWeb.Thymeleaf.model.Product;
import LapTrinhWeb.Thymeleaf.model.User;
import LapTrinhWeb.Thymeleaf.repository.CategoryRepository;
import LapTrinhWeb.Thymeleaf.repository.ProductRepository;
import LapTrinhWeb.Thymeleaf.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> findAllSortedByPrice() { return productRepository.findAllByOrderByPriceAsc(); }
    public List<Product> findByCategoryId(Long categoryId) { return productRepository.findDistinctByCategoriesId(categoryId); }

    public Product findById(Long id) { return productRepository.findById(id).orElse(null); }

    public Product save(Product p, Long userId, Set<Long> categoryIds) {
        if (userId != null) {
            User u = userRepository.findById(userId).orElse(null);
            p.setUser(u);
        }
        if (categoryIds != null) {
            Set<Category> cats = new HashSet<>();
            for (Long cid : categoryIds) {
                Category c = categoryRepository.findById(cid).orElse(null);
                if (c != null) cats.add(c);
            }
            p.setCategories(cats);
        }
        return productRepository.save(p);
    }

    public void delete(Long id) { productRepository.deleteById(id); }
}
