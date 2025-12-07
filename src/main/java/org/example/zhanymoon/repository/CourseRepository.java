package org.example.zhanymoon.repository;

import org.example.zhanymoon.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}