package com.example.school.repositories;

import com.example.school.entities.OfficeAssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfficeAssignmentRepository extends CrudRepository<OfficeAssignmentEntity,Long> {
}
