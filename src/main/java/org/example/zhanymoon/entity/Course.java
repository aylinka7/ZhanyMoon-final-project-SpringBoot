package org.example.zhanymoon.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameRu;
    private String nameKy;
    private String nameEn;
    private String age;
    private String descriptionRu;
    private String descriptionKy;
    private String descriptionEn;
    private Integer price;
    private String imageUrl;
}