package com.example.school.repositories;

import com.example.school.entities.CourseEnitity;
import com.example.school.entities.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<CourseEnitity,Long> {
    Page<CourseEnitity> findAll(Pageable pageable);

}
