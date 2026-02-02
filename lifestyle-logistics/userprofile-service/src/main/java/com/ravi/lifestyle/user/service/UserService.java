package com.ravi.lifestyle.user.service;
import com.ravi.lifestyle.user.model.User;
import com.ravi.lifestyle.user.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository repo;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  public UserService(UserRepository repo) {
    this.repo = repo; }

  public User register(User u) {
    u.setPassword(encoder.encode(u.getPassword()));
    return repo.save(u);
  }
  public Optional<User> findByUsername(String username) {
    return repo.findByUsername(username);
  }
  public User update(Long id, User u) {
    //User e = repo.findById(id).orElseThrow();
    User user = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    user.setEmail(u.getEmail());
    user.setFullName(u.getFullName());
    user.setCountry(u.getCountry());
    return repo.save(user);
  }
}
