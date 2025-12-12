package LapTrinhWeb.Thymeleaf.service;

import LapTrinhWeb.Thymeleaf.model.User;
import LapTrinhWeb.Thymeleaf.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() { return userRepository.findAll(); }
    public User findById(Long id) { return userRepository.findById(id).orElse(null); }
    public User save(User u) { return userRepository.save(u); }
    public void delete(Long id) { userRepository.deleteById(id); }
}
