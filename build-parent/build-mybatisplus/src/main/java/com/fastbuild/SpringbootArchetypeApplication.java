package com.fastbuild;

import com.fastbuild.database.DruidDbProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.fastbuild.mapper")
public class SpringbootArchetypeApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringbootArchetypeApplication.class);
		//设置banner的模式 OFF:隐藏 CONSOLE:控制台
		springApplication.setBannerMode(Banner.Mode.CONSOLE);
		//启动springboot应用程序
		springApplication.run(args);
	}
}
