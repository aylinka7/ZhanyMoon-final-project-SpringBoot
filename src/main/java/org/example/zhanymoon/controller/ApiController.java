package org.example.zhanymoon.controller;

import org.example.zhanymoon.dto.ApplicationRequest;
import org.example.zhanymoon.entity.Application;
import org.example.zhanymoon.entity.Course;
import org.example.zhanymoon.repository.ApplicationRepository;
import org.example.zhanymoon.repository.CourseRepository;
import jakarta.validation.Valid;
import org.example.zhanymoon.service.TelegramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://zhany-moon-final-project-web.vercel.app")
public class ApiController {

    private final CourseRepository courseRepo;
    private final ApplicationRepository appRepo;
    private final TelegramService telegramService;

    public ApiController(CourseRepository courseRepo, ApplicationRepository appRepo, TelegramService telegramService) {
        this.courseRepo = courseRepo;
        this.appRepo = appRepo;
        this.telegramService = telegramService;
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    @PostMapping("/applications")
    public ResponseEntity<String> apply(@Valid @RequestBody ApplicationRequest req) {
        Application app = new Application();
        app.setChildName(req.childName());
        app.setChildAge(req.childAge());
        app.setParentName(req.parentName());
        app.setPhone(req.phone());
        app.setCourseName(req.courseName());
        app.setMessage(req.message());
//        appRepo.save(app);
        Application savedApp = appRepo.save(app);
        String notificationText = """
              Новая заявка! 🎉
              
              Имя ребёнка: %s
              Возраст ребёнка: %s
              Имя родителя: %s
              Телефон: %s
              Курс: %s
              Сообщение: %s
              """.formatted(
                savedApp.getChildName(),
                savedApp.getChildAge(),
                savedApp.getParentName(),
                savedApp.getPhone(),
                savedApp.getCourseName(),
                savedApp.getMessage() != null ? savedApp.getMessage() : "—"
        );

        telegramService.sendNotification(notificationText);
        return ResponseEntity.ok("Спасибо! Мы свяжемся с вами в ближайшее время");
    }

    @GetMapping("/applications")
    public List<Application> getApplications() {
        return appRepo.findAll();
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        appRepo.deleteById(id);
        return ResponseEntity.ok("Заявка удалена");
    }
}