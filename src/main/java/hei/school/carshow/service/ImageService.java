package hei.school.carshow.service;

import hei.school.carshow.db.entity.Image;
import hei.school.carshow.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    public Image insertImage(Image image) {
        return imageRepository.save(image);
    }

    public Optional<Image> getImageById(String id) {
        return imageRepository.findById(id);
    }

    public Image updateImage(String id,Image updatedImage) {
        Optional<Image> existingImage = imageRepository.findById(id);

        if (existingImage.isPresent()) {
            Image imageToUpdate = existingImage.get();

            imageToUpdate.setUrl(updatedImage.getUrl());

            return imageRepository.save(imageToUpdate);
        } else {
            throw new IllegalArgumentException("Invalid ID " + id);
        }
    }

    public void deleteImageById(String id) {
        imageRepository.deleteById(id);
    }
}
