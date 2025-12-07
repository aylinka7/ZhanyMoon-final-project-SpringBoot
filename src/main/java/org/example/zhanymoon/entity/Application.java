package org.example.zhanymoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String childName;
    private String childAge;
    private String parentName;
    private String phone;
    private String courseName;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status = "NEW";
}