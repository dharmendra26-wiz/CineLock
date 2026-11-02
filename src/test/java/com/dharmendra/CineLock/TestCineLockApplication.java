package com.dharmendra.CineLock;

import org.springframework.boot.SpringApplication;

public class TestCineLockApplication {

	public static void main(String[] args) {
		SpringApplication.from(CineLockApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
