package org.example.zhanymoon.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageController {

  @GetMapping("/images/courses/{imageName}")  // ← ИСПРАВЛЕНО: было /courses/images/
  public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
    try {
      // Путь согласно вашей структуре: static.images.courses
      Resource resource = new ClassPathResource("static/images/courses/" + imageName);

      if (!resource.exists()) {
        System.out.println("Image not found: " + imageName);
        return ResponseEntity.notFound().build();
      }

      // Используем InputStream для работы внутри JAR
      InputStream inputStream = resource.getInputStream();
      byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);

      // Определяем MediaType
      MediaType mediaType = getMediaType(imageName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(mediaType);
      headers.setContentLength(imageBytes.length);

      return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error loading image: " + imageName + " - " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  private MediaType getMediaType(String filename) {
    String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    return switch (extension) {
      case "png" -> MediaType.IMAGE_PNG;
      case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
      case "gif" -> MediaType.IMAGE_GIF;
      case "svg" -> MediaType.valueOf("image/svg+xml");
      case "webp" -> MediaType.valueOf("image/webp");
      default -> MediaType.APPLICATION_OCTET_STREAM;
    };
  }
}


