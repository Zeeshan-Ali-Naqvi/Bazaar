package com.kugelblitz.bazaar.controller;

import com.kugelblitz.bazaar.entity.Role;
import com.kugelblitz.bazaar.entity.User;
import com.kugelblitz.bazaar.repository.RoleRepository;
import com.kugelblitz.bazaar.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  private final UserRepository userRepository;
  private final PasswordEncoder bCryptPasswordEncoder;
  private final RoleRepository roleRepository;

  public AuthController(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    String username = authentication.getName();
    model.addAttribute("username", username);
    return "home";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/registerUser")
  public String register(@ModelAttribute("user") User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    Optional<Role> customerRole = roleRepository.findByName("ROLE_CUSTOMER");
    customerRole.ifPresent(role -> user.setRoles(Collections.singleton(role)));
    userRepository.save(user);
    return "redirect:/login";
  }

  @GetMapping("/logout")
  public String logout() {
    return "redirect:/login";
  }
}
