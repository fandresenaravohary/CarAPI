package hei.school.carshow.controller;

import hei.school.carshow.db.entity.Image;
import hei.school.carshow.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ImageController {
    @Autowired
    public ImageService imageService;

    @GetMapping("/Images")
    public List<Image> findAllImage() {
        return imageService.getAllImage();
    }

    @GetMapping("/image/{id}")
    public Optional<Image> findImageById(@PathVariable String id) {
        return imageService.getImageById(id);
    }

    @PostMapping("/admin/image")
    public Image newImage(@RequestBody Image image) {
        return imageService.insertImage(image);
    }

    @PutMapping("/admin/image/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable String id, @RequestBody Image image) {
        Image updatedImage = imageService.updateImage(id, image);
        if (updatedImage != null) {
            return ResponseEntity.ok(updatedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/delete/image/{id}")
    public void deleteImageById(@PathVariable String id) {
        imageService.deleteImageById(id);
    }
}
