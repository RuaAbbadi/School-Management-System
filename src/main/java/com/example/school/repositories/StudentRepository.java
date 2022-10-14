package com.example.school.repositories;

import com.example.school.entities.StudentEnitity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEnitity,Long> {
}
