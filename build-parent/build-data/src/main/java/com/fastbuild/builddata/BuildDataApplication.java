package com.fastbuild.builddata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fastbuild.builddata.mapper")
public class BuildDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildDataApplication.class, args);
	}
}
