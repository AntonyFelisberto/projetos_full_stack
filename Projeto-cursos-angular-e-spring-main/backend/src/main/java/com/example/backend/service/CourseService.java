package com.example.backend.service;

import com.example.backend.model.Courses;
import com.example.backend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Courses> getCourseList() {
        return courseRepository.findAll();
    }

    public void deleteAll() {
        courseRepository.deleteAll();
    }

    public void save(Courses courses) {
        courseRepository.save(courses);
    }

}
