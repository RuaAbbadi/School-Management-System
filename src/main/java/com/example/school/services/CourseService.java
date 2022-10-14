package com.example.school.services;

import com.example.school.dto.Course;
import com.example.school.dto.Department;
import com.example.school.dto.FormCourse;
import com.example.school.dto.FormDepartment;
import com.example.school.entities.CourseEnitity;
import com.example.school.entities.DepartmentEntity;
import com.example.school.entities.InstructorEntity;
import com.example.school.repositories.CourseRepository;
import com.example.school.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final DepartmentRepository departmentRepository;
    private  final ModelMapper mapper;


    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public List<Course> findAll() {
        Iterable<CourseEnitity> entities = courseRepository.findAll();
        return mapper.map(entities, new TypeToken<List<Course>>() {
        }.getType());
    }


    public boolean create(FormCourse formCourse) {
        CourseEnitity entity = mapper.map(formCourse, CourseEnitity.class);
        if (formCourse.getDepartmentId() != null) {
            Optional<DepartmentEntity> department = departmentRepository.findById(formCourse.getDepartmentId());
            department.ifPresent(entity::setDepartment);
        }
        try {
            courseRepository.save(entity);
            return true;
        } catch (DataIntegrityViolationException ignored) {
        }
        return false;
    }

    public Course findById(long id) {
        CourseEnitity entity = courseRepository.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, Course.class);
        }
        return null;
    }

    public boolean update(FormCourse formCourse) {
        CourseEnitity courseEnitity = courseRepository.findById(formCourse.getId()).orElse(null);
        if (courseEnitity == null) {
            return false;
        }
        if (formCourse.getDepartmentId() != null) {
            DepartmentEntity departmentEntity = departmentRepository.findById(formCourse.getDepartmentId()).orElse(null);
            if (departmentEntity == null) {
                return false;
            }
            courseEnitity.setDepartment(departmentEntity);
        } else {
            courseEnitity.setDepartment(null);
        }
        courseEnitity.setTitle(formCourse.getTitle());
        courseEnitity.setCredits(formCourse.getCredits());
        courseRepository.save(courseEnitity);
        return true;
    }

    public Course deleteById(long id) {
        CourseEnitity courseEnitity = courseRepository.findById(id).orElse(null);
        if (courseEnitity != null) {
            Course course = mapper.map(courseEnitity, Course.class);
            courseRepository.delete(courseEnitity);
            return course;
        }
        return null;
    }
}