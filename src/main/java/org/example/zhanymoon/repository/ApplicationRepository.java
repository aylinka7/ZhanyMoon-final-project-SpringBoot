package org.example.zhanymoon.repository;

import org.example.zhanymoon.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {}