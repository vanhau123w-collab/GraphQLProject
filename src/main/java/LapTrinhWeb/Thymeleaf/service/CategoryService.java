package LapTrinhWeb.Thymeleaf.service;

import LapTrinhWeb.Thymeleaf.model.Category;
import LapTrinhWeb.Thymeleaf.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() { return categoryRepository.findAll(); }
    public Category findById(Long id) { return categoryRepository.findById(id).orElse(null); }
    public Category save(Category c) { return categoryRepository.save(c); }
    public void delete(Long id) { categoryRepository.deleteById(id); }
}
