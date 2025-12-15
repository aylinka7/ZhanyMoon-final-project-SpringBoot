package org.example.zhanymoon.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class ImageController {

  @GetMapping("/courses/images/{imageName}")
  public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
    try {
      Resource resource = new ClassPathResource("static/courses/images/" + imageName);
      byte[] image = Files.readAllBytes(resource.getFile().toPath());

      return ResponseEntity.ok()
              .contentType(MediaType.IMAGE_PNG)
              .body(image);
    } catch (IOException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
