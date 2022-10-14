package com.example.school.services;


import com.example.school.dto.Instructor;
import com.example.school.dto.Student;
import com.example.school.entities.InstructorEntity;
import com.example.school.entities.StudentEnitity;
import com.example.school.repositories.InstructorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final ModelMapper mapper;


    public InstructorService(InstructorRepository instructorRepository, ModelMapper mapper) {
        this.instructorRepository = instructorRepository;
        this.mapper = mapper;
    }

    public List<Instructor> findAll() {
        Iterable<InstructorEntity> entities = instructorRepository.findAll();
        return mapper.map(entities, new TypeToken<List<Instructor>>() {
        }.getType());
    }

    public boolean create(Instructor instructor) {
        InstructorEntity enitity=  mapper.map(instructor, InstructorEntity.class);
        try {
            instructorRepository.save(enitity);
            return true;
        } catch (DataIntegrityViolationException ignored) {
        }
        return false;
    }

    public Instructor findById(long id) {
        InstructorEntity entity = instructorRepository.findById(id).orElse(null);
        if (entity != null) {
            return mapper.map(entity, Instructor.class);
        }
        return null;
    }

    public boolean update(Instructor instructor) {
        Optional<InstructorEntity> instructorEntity = instructorRepository.findById(instructor.getId());
        if (instructorEntity.isPresent()){
            InstructorEntity instructorEntity1 = instructorEntity.get();
            instructorEntity1.setFirstMidName(instructor.getFirstMidName());
            instructorEntity1.setLastName(instructor.getLastName());
            instructorEntity1.setHireDate(instructor.getHireDate());
            instructorRepository.save(instructorEntity1);
            return true;

        }
        return false;
    }

    public Instructor deleteById(long id) {
        InstructorEntity instructorEntity=instructorRepository.findById(id).orElse(null);
        if(instructorEntity!=null){
            Instructor instructor=mapper.map(instructorEntity,Instructor.class);
            instructorRepository.delete(instructorEntity);
            return instructor;
        }
        return null;

    }

}
