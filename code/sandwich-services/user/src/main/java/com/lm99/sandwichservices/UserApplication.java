package com.lm99.sandwichservices;

import com.lm99.sandwichservices.role.Role;
import com.lm99.sandwichservices.role.RoleService;
import com.lm99.sandwichservices.user.User;
import com.lm99.sandwichservices.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ATTENTION: This will only be here while Spring Security is not configured.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().anyRequest();
    }

    // ATTENTION: This will only be here while the application is being tested.
    @Bean
    public CommandLineRunner commandLineRunner(RoleService roleService, UserService userService) {
        return args -> {
            Role adminRole = new Role(1L, "ADMIN", "Administrates the application");
            Role operatorRole = new Role(2L, "OPERATOR", "Operates the application");
            Role customerRole = new Role(3L, "CUSTOMER", "Uses the application");
            roleService.createRole(adminRole);
            roleService.createRole(operatorRole);
            roleService.createRole(customerRole);
            userService.createUser(new User(
                    1L,
                    "I Am",
                    "Admin",
                    "admin@email.com",
                    this.passwordEncoder().encode("Admin@123"),
                    true,
                    adminRole
            ));
            userService.createUser(new User(
                    2L,
                    "I Am",
                    "Operator",
                    "operator@email.com",
                    this.passwordEncoder().encode("Operator@123"),
                    true,
                    operatorRole
            ));
            userService.createUser(new User(
                    3L,
                    "I Am",
                    "Customer",
                    "customer@email.com",
                    this.passwordEncoder().encode("Customer@123"),
                    true,
                    customerRole
            ));

        };
    }

}
