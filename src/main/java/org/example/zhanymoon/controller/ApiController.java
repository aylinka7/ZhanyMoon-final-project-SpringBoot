package org.example.zhanymoon.controller;

import org.example.zhanymoon.dto.ApplicationRequest;
import org.example.zhanymoon.entity.Application;
import org.example.zhanymoon.entity.Course;
import org.example.zhanymoon.repository.ApplicationRepository;
import org.example.zhanymoon.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ApiController {

    private final CourseRepository courseRepo;
    private final ApplicationRepository appRepo;

    public ApiController(CourseRepository courseRepo, ApplicationRepository appRepo) {
        this.courseRepo = courseRepo;
        this.appRepo = appRepo;
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
        appRepo.save(app);
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