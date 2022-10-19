package com.example.school;

import com.example.school.entities.UserEntity;
import com.example.school.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);

	}

	@Bean
	ModelMapper createModelMapper() {
		return new ModelMapper();
	}


	private UserEntity newUser(String username, String password, String authority) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(password);
		user.setAuthority(authority);
		return user;
	}

	@Bean
	CommandLineRunner createUsers(UsersRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			repository.save(newUser("admin", passwordEncoder.encode("admin"), "ADMIN"));
			repository.save(newUser("user", passwordEncoder.encode("user"), "USER"));
		};
	}
}
