package com.lm99.sandwichservices.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRoleId(Long roleId);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
