package com.example.school.repositories;

import com.example.school.entities.InstructorEntity;
import com.example.school.entities.StudentEnitity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEnitity,Long> {
    Page<StudentEnitity> findAll(Pageable pageable);
}
