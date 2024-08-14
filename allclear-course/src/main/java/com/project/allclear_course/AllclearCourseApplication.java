package com.project.allclear_course;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AllclearCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllclearCourseApplication.class, args);
	}

	// 빈생성
	@Bean
	Hibernate5Module hibernate5Module(){
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		//hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5Module;
	}

}
