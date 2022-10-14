package com.example.school.repositories;


import com.example.school.entities.InstructorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends CrudRepository<InstructorEntity,Long> {
}
