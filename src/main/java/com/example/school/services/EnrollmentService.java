package com.example.school.services;

import com.example.school.dto.Course;
import com.example.school.dto.Enrollment;
import com.example.school.dto.FormCourse;
import com.example.school.dto.FormEnrollment;
import com.example.school.entities.CourseEnitity;
import com.example.school.entities.DepartmentEntity;
import com.example.school.entities.EnrollmentEntity;
import com.example.school.entities.StudentEnitity;
import com.example.school.repositories.CourseRepository;
import com.example.school.repositories.EnrollmentRepository;
import com.example.school.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private  final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final ModelMapper mapper;


    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper mapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    public List<Enrollment> findAll() {
        Iterable<EnrollmentEntity> entities = enrollmentRepository.findAll();
        return mapper.map(entities, new TypeToken<List<Enrollment>>() {
        }.getType());
    }

    public boolean create(FormEnrollment formEnrollment) {
        EnrollmentEntity entity = mapper.map(formEnrollment, EnrollmentEntity.class);
        if ((formEnrollment.getStudentId() != null)&& (formEnrollment.getCourseId()!=null) ){
            Optional<StudentEnitity> studentEnitity = studentRepository.findById(formEnrollment.getStudentId());
            Optional<CourseEnitity> courseEnitity = courseRepository.findById(formEnrollment.getCourseId());

            studentEnitity.ifPresent(entity::setStudentId);
            courseEnitity.ifPresent(entity::setCourseId);

        }
        try {
            enrollmentRepository.save(entity);
            return true;
        } catch (DataIntegrityViolationException ignored) {
        }
        return false;
    }


    public Enrollment findById(long id) {
        EnrollmentEntity entity = enrollmentRepository.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, Enrollment.class);
        }
        return null;
    }

    public boolean update(FormEnrollment formEnrollment) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(formEnrollment.getStudentId()).orElse(null);
        if (enrollment == null) {
            return false;
        }
        if((formEnrollment.getStudentId() != null) &&(formEnrollment.getCourseId() != null))  {
            StudentEnitity studentEnitity = studentRepository.findById(formEnrollment.getStudentId()).orElse(null);
            CourseEnitity courseEnitity = courseRepository.findById(formEnrollment.getCourseId()).orElse(null);

            if (studentEnitity == null && courseEnitity==null ) {
                return false;
            }
            studentEnitity.setEnrollments((Collection<EnrollmentEntity>) studentEnitity);
            courseEnitity.setEnrollments((Collection<EnrollmentEntity>) courseEnitity);
        } else {
            enrollment.setStudentId(null);
            enrollment.setCourseId(null);
        }

        enrollment.setGrade(formEnrollment.getGrade());
        enrollmentRepository.save(enrollment);
        return true;
    }

    public Enrollment deleteById(long id) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findById(id).orElse(null);
        if (enrollmentEntity != null) {
            Enrollment enrollment = mapper.map(enrollmentEntity, Enrollment.class);
            enrollmentRepository.delete(enrollmentEntity);
            return enrollment;
        }
        return null;
    }
}
