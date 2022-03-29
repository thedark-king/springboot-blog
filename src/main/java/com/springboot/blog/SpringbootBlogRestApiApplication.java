package com.springboot.blog;

import com.springboot.blog.entity.Roles;
import com.springboot.blog.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Role;

@SpringBootApplication
public class SpringbootBlogRestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		Roles adminRole = new Roles();

		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Roles userRole = new Roles();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);


	}
}
