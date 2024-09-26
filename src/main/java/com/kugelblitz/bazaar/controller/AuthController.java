package com.kugelblitz.bazaar.controller;

import com.kugelblitz.bazaar.entity.User;
import com.kugelblitz.bazaar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  private final UserRepository userRepository;
  private final PasswordEncoder bCryptPasswordEncoder;

  public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = passwordEncoder;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    String username = authentication.getName();
    model.addAttribute("username", username);
    return "home";
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @PostMapping("/registerUser")
  public String register(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");
    userRepository.save(user);
    return "redirect:/login";
  }

  @GetMapping("/logout")
  public String logout() {
    return "redirect:/login";
  }
}
