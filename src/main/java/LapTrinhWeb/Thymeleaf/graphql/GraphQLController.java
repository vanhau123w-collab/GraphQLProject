package LapTrinhWeb.Thymeleaf.graphql;

import LapTrinhWeb.Thymeleaf.model.Category;
import LapTrinhWeb.Thymeleaf.model.Product;
import LapTrinhWeb.Thymeleaf.model.User;
import LapTrinhWeb.Thymeleaf.service.CategoryService;
import LapTrinhWeb.Thymeleaf.service.ProductService;
import LapTrinhWeb.Thymeleaf.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class GraphQLController {
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public GraphQLController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @QueryMapping
    public List<Product> productsSortedByPriceAsc() {
        return productService.findAllSortedByPrice();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<User> users() { return userService.findAll(); }

    @QueryMapping
    public List<Category> categories() { return categoryService.findAll(); }

    @MutationMapping
    public User createUser(@Argument String fullname, @Argument String email, @Argument String password, @Argument String phone) {
        User u = new User();
        u.setFullname(fullname); u.setEmail(email); u.setPassword(password); u.setPhone(phone);
        return userService.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument String fullname, @Argument String email, @Argument String password, @Argument String phone) {
        User u = userService.findById(id);
        if (u == null) return null;
        if (fullname != null) u.setFullname(fullname);
        if (email != null) u.setEmail(email);
        if (password != null) u.setPassword(password);
        if (phone != null) u.setPhone(phone);
        return userService.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userService.delete(id);
        return true;
    }

    @MutationMapping
    public Category createCategory(@Argument String name, @Argument String images) {
        Category c = new Category(); c.setName(name); c.setImages(images);
        return categoryService.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument String name, @Argument String images) {
        Category c = categoryService.findById(id);
        if (c == null) return null;
        if (name != null) c.setName(name);
        if (images != null) c.setImages(images);
        return categoryService.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        categoryService.delete(id); return true;
    }

    @MutationMapping
    public Product createProduct(@Argument String title, @Argument Integer quantity, @Argument String desc, @Argument Double price, @Argument Long userId, @Argument Set<Long> categoryIds) {
        Product p = new Product();
        p.setTitle(title); p.setQuantity(quantity); p.setDesc(desc); p.setPrice(java.math.BigDecimal.valueOf(price));
        return productService.save(p, userId, categoryIds);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) { productService.delete(id); return true; }

    @SchemaMapping
    public List<Category> categories(Product product) { return List.copyOf(product.getCategories()); }

    @SchemaMapping
    public User user(Product product) { return product.getUser(); }

    @QueryMapping
    public User user(@Argument Long id) { return userService.findById(id); }

    @QueryMapping
    public Category category(@Argument Long id) { return categoryService.findById(id); }
}
