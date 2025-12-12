package LapTrinhWeb.Thymeleaf.repository;

import LapTrinhWeb.Thymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
