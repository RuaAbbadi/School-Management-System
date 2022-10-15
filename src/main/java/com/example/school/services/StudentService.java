package com.example.school.services;


import com.example.school.dto.Instructor;
import com.example.school.dto.PageDto;
import com.example.school.dto.Student;
import com.example.school.entities.InstructorEntity;
import com.example.school.entities.StudentEnitity;
import com.example.school.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:school.properties")
public class StudentService {

    @Value("${school.max.page.size}")
    private int maxPageSize;

    private final StudentRepository studentRepository;

    private final ModelMapper mapper;


    public StudentService(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public List<Student> findAll() {
        Iterable<StudentEnitity> entities = studentRepository.findAll();
        return mapper.map(entities, new TypeToken<List<Student>>() {
        }.getType());
    }

    public PageDto<Student> list(int currentPage, boolean ascending, String orderBy) {
        Sort sort = ascending ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(currentPage, maxPageSize, sort);
        Page<StudentEnitity> page = studentRepository.findAll(pageable);
        PageDto<Student> pageDto = mapper.map(page, new TypeToken<PageDto<Student>>() {
        }.getType());
        pageDto.setAscending(ascending);
        pageDto.setOrderBy(orderBy);
        return pageDto;
    }

    public boolean create(Student student) {
      StudentEnitity enitity=  mapper.map(student, StudentEnitity.class);
        try {
            studentRepository.save(enitity);
            return true;
        } catch (DataIntegrityViolationException ignored) {
        }
        return false;
    }

    public Student findById(long id) {
        StudentEnitity entity = studentRepository.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, Student.class);
        }
        return null;
    }

    public boolean update(Student student) {
        Optional<StudentEnitity> studentEnitityOptional = studentRepository.findById(student.getId());
        if (studentEnitityOptional.isPresent()){
            StudentEnitity studentEnitity = studentEnitityOptional.get();
            studentEnitity.setFirstMidName(student.getFirstMidName());
            studentEnitity.setLastName(student.getLastName());
            studentEnitity.setEnrollmentDate(student.getEnrollmentDate());
            studentRepository.save(studentEnitity);
            return true;

        }
        return false;
    }

    public Student deleteById(long id) {
        StudentEnitity studentEnitity=studentRepository.findById(id).orElse(null);
        if(studentEnitity!=null){
            Student student=mapper.map(studentEnitity,Student.class);
            studentRepository.delete(studentEnitity);
            return student;
        }
        return null;

    }
}
