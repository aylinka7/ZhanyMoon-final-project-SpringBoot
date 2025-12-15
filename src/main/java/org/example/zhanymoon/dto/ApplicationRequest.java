package org.example.zhanymoon.dto;

import jakarta.validation.constraints.*;

public record ApplicationRequest(
        @NotBlank(message = "Имя ребёнка обязательно")
        String childName,

        @NotBlank(message = "Возраст обязателен")
        String childAge,

        @NotBlank
        String parentName,

        @NotBlank(message = "Телефон обязателен")
        String phone,

        @NotBlank
        String courseName,

        String message
) {}