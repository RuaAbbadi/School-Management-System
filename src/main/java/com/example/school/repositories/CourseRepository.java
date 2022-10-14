package com.example.school.repositories;

import com.example.school.entities.CourseEnitity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<CourseEnitity,Long> {
}
