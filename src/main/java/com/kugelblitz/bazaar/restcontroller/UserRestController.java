package com.kugelblitz.bazaar.restcontroller;

import com.kugelblitz.bazaar.entity.User;
import com.kugelblitz.bazaar.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

  private final UserRepository userRepository;

  public UserRestController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userRepository.findAll();
  }
}
