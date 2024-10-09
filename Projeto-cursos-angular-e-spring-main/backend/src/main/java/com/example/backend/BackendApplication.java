package com.example.backend;

import com.example.backend.model.Courses;
import com.example.backend.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class BackendApplication {

    private CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initDataBase(){
        return args -> {
            courseService.deleteAll();

            Courses courses = new Courses();
            courses.setName("Angular");
            courses.setCategory("FrontEnd");

            courseService.save(courses);
        };
    }

}
