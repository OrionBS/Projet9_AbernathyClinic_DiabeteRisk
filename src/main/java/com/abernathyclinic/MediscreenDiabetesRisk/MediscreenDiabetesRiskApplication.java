package com.abernathyclinic.MediscreenDiabetesRisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MediscreenDiabetesRiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenDiabetesRiskApplication.class, args);
	}

}
