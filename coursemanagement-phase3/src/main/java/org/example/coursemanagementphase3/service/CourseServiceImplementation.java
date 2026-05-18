package org.example.coursemanagementphase3.service;

import org.example.coursemanagementphase3.dto.CourseDto;
import org.example.coursemanagementphase3.entity.Course;
import org.example.coursemanagementphase3.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImplementation implements CourseService{

    private final CourseRepository courseRepository;

    public CourseServiceImplementation(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDto createCourse(CourseDto dto) {
        Course course = new Course();
        course.setTitle(dto.title());
        course.setCategory(dto.category());
        Course saved = courseRepository.save(course);
        return new CourseDto(saved.getId(), saved.getTitle(), saved.getCategory());
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> {
                    return new CourseDto(
                            course.getId(),
                            course.getTitle(),
                            course.getCategory()
                    );

                })
                .toList();
    }
}
