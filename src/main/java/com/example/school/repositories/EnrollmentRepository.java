package com.example.school.repositories;

import com.example.school.entities.EnrollmentEntity;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository  extends CrudRepository<EnrollmentEntity,Long> {
}
