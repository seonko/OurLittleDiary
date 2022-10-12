package com.seonko.OurLittleDiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OurLittleDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurLittleDiaryApplication.class, args);
	}

}
