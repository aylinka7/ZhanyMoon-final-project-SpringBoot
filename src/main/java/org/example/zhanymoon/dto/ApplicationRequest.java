package org.example.zhanymoon.dto;

import jakarta.validation.constraints.*;

public record ApplicationRequest(
        @NotBlank(message = "Имя ребёнка обязательно")
        String childName,

        @NotBlank
        @Pattern(regexp = "\\d{1,2}", message = "Возраст — только цифры")
        String childAge,

        @NotBlank
        String parentName,

        @NotBlank
        @Pattern(regexp = "\\+?\\d{10,15}", message = "Неверный номер телефона")
        String phone,

        @NotBlank
        String courseName,

        String message
) {}