package com.example.school.services;


import com.example.school.dto.Department;
import com.example.school.dto.FormDepartment;
import com.example.school.entities.DepartmentEntity;
import com.example.school.entities.InstructorEntity;
import com.example.school.repositories.DepartmentRepository;
import com.example.school.repositories.InstructorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final InstructorRepository instructorRepository;
    final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, InstructorRepository instructorRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.instructorRepository = instructorRepository;
        this.mapper = mapper;
    }

    public List<Department> findAll() {
        Iterable<DepartmentEntity> entities = departmentRepository.findAll();
        return mapper.map(entities, new TypeToken<List<Department>>() {
        }.getType());
    }

    public boolean create(FormDepartment formDepartment) {
        DepartmentEntity entity = mapper.map(formDepartment, DepartmentEntity.class);
        if (formDepartment.getAdministratorId() != null) {
            Optional<InstructorEntity> administrator = instructorRepository.findById(formDepartment.getAdministratorId());
            administrator.ifPresent(entity::setAdministrator);
        }
        try {
            departmentRepository.save(entity);
            return true;
        } catch (DataIntegrityViolationException ignored) {
        }
        return false;
    }

    public Department findById(long id) {
        DepartmentEntity entity = departmentRepository.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, Department.class);
        }
        return null;
    }

    public boolean update(FormDepartment formDepartment) {
        DepartmentEntity departmentEntity = departmentRepository.findById(formDepartment.getDepartmentId()).orElse(null);
        if (departmentEntity == null) {
            return false;
        }
        if (formDepartment.getAdministratorId() != null) {
            InstructorEntity instructorEntity = instructorRepository.findById(formDepartment.getAdministratorId()).orElse(null);
            if (instructorEntity == null) {
                return false;
            }
            departmentEntity.setAdministrator(instructorEntity);
        } else {
            departmentEntity.setAdministrator(null);
        }
        departmentEntity.setName(formDepartment.getName());
        departmentEntity.setBudget(formDepartment.getBudget());
        departmentEntity.setStartDate(formDepartment.getStartDate());
        departmentRepository.save(departmentEntity);
        return true;
    }


    public Department deleteById(long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElse(null);
        if (departmentEntity != null) {
            Department department = mapper.map(departmentEntity, Department.class);
            departmentRepository.delete(departmentEntity);
            return department;
        }
        return null;
    }
}
