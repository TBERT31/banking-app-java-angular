package com.tbert31.banking.repositories;

import com.tbert31.banking.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(String roleName);
}
