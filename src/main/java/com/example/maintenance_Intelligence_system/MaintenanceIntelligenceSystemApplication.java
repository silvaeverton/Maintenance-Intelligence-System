package com.example.maintenance_Intelligence_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MaintenanceIntelligenceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceIntelligenceSystemApplication.class, args);
	}

}
