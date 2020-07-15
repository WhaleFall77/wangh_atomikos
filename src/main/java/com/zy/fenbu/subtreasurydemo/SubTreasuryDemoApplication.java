package com.zy.fenbu.subtreasurydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@MapperScan(basePackages = "com.zy.fenbu.subtreasurydemo")
@SpringBootApplication
public class SubTreasuryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubTreasuryDemoApplication.class, args);
	}

}
