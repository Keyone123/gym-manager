package com.keyone.gym_manager;

import org.springframework.boot.SpringApplication;

public class TestGymManagerApplication {

	public static void main(String[] args) {
		SpringApplication.from(GymManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
