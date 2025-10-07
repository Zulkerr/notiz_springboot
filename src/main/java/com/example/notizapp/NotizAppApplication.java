package com.example.notizapp;

import com.example.notizapp.repository.CategoryRepository;
import com.example.notizapp.model.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotizAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotizAppApplication.class, args);
	}
	@Bean
	CommandLineRunner initData(CategoryRepository categoryRepository) {
		return args -> {
			categoryRepository.save(new Category("Arbeit"));
			categoryRepository.save(new Category("Privat"));
			categoryRepository.save(new Category("Ideen"));
		};

	}
}
