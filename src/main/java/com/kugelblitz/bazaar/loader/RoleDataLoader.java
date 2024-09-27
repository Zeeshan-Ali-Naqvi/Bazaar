package com.kugelblitz.bazaar.loader;

import com.kugelblitz.bazaar.entity.Role;
import com.kugelblitz.bazaar.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleDataLoader implements CommandLineRunner {
  private final RoleRepository roleRepository;

  public RoleDataLoader(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void run(String... args) {
    if (roleRepository.count() == 0) {
      roleRepository.save(new Role(null, "ROLE_ADMIN"));
      roleRepository.save(new Role(null, "ROLE_CUSTOMER"));
      roleRepository.save(new Role(null, "ROLE_SELLER"));
      roleRepository.save(new Role(null, "ROLE_GUEST"));
      roleRepository.save(new Role(null, "ROLE_SUPPORT"));
    }
  }
}
