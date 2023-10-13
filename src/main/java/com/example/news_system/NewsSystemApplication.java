package com.example.news_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class NewsSystemApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(NewsSystemApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
