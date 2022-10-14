package com.example.school.services;


import com.example.school.dto.Course;
import com.example.school.dto.FormCourse;
import com.example.school.dto.FormOfficeAssignment;
import com.example.school.dto.OfficeAssignment;
import com.example.school.entities.CourseEnitity;
import com.example.school.entities.DepartmentEntity;
import com.example.school.entities.InstructorEntity;
import com.example.school.entities.OfficeAssignmentEntity;
import com.example.school.repositories.InstructorRepository;
import com.example.school.repositories.OfficeAssignmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeAssignmentService {

    private final OfficeAssignmentRepository officeAssignmentRepository;

    private final InstructorRepository instructorRepository;

    private final ModelMapper mapper;




    public OfficeAssignmentService(OfficeAssignmentRepository officeAssignmentRepository, InstructorRepository instructorRepository, ModelMapper mapper) {
        this.officeAssignmentRepository = officeAssignmentRepository;
        this.instructorRepository = instructorRepository;
        this.mapper = mapper;
    }

    public List<OfficeAssignment> findAll() {
        Iterable<OfficeAssignmentEntity> entities = officeAssignmentRepository.findAll();
        return mapper.map(entities, new TypeToken<List<OfficeAssignment>>() {
        }.getType());
    }

    public boolean create(FormOfficeAssignment formOfficeAssignment) {
        OfficeAssignmentEntity entity = mapper.map(formOfficeAssignment, OfficeAssignmentEntity.class);
        if (formOfficeAssignment.getInstId() != null) {
            Optional<InstructorEntity> instructor = instructorRepository.findById(formOfficeAssignment.getInstId());
            instructor.ifPresent(entity::setInstructor);
        }
        try {
            officeAssignmentRepository.save(entity);
            return true;
        } catch (DataIntegrityViolationException ignored) {
        }
        return false;
    }

    public OfficeAssignment findById(long id) {
        OfficeAssignmentEntity entity = officeAssignmentRepository.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, OfficeAssignment.class);
        }
        return null;
    }

    public boolean update(FormOfficeAssignment formOfficeAssignment) {
        OfficeAssignmentEntity officeAssignment = officeAssignmentRepository.findById(formOfficeAssignment.getId()).orElse(null);
        if (officeAssignment == null) {
            return false;
        }
        if (formOfficeAssignment.getInstId()!= null) {
            InstructorEntity instructorEntity = instructorRepository.findById(formOfficeAssignment.getInstId()).orElse(null);
            if (instructorEntity == null) {
                return false;
            }
            officeAssignment.setInstructor(instructorEntity);
        } else {
            officeAssignment.setInstructor(null);
        }
        officeAssignment.setLocation(formOfficeAssignment.getLocation());
        officeAssignmentRepository.save(officeAssignment);
        return true;
    }

    public OfficeAssignment deleteById(long id) {
        OfficeAssignmentEntity officeAssignment = officeAssignmentRepository.findById(id).orElse(null);
        if (officeAssignment != null) {
            OfficeAssignment officeAssignment1 = mapper.map(officeAssignment, OfficeAssignment.class);
            officeAssignmentRepository.delete(officeAssignment);
            return officeAssignment1;
        }
        return null;
    }

}
