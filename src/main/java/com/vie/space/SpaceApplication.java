package com.vie.space;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.vie.space.mapper")
public class SpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceApplication.class, args);
	}

}
