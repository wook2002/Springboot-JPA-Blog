package com.jae.prj05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class Prj05Application {

	public static void main(String[] args) {
		System.out.println("===========test 1 ===========");
		SpringApplication.run(Prj05Application.class, args);
		System.out.println("===========test 2 ===========");
	}

}
