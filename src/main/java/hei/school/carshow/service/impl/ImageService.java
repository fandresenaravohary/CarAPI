package hei.school.carshow.service.impl;

import hei.school.carshow.entity.Image;
import hei.school.carshow.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Image> getImageById(UUID id) {
        return imageRepository.findById(id);
    }

    public Image updateImage(UUID id, Image updatedImage) {
        Optional<Image> existingImage = imageRepository.findById(id);

        if (existingImage.isPresent()) {
            Image imageToUpdate = existingImage.get();

            imageToUpdate.setUrl(updatedImage.getUrl());

            return imageRepository.save(imageToUpdate);
        } else {
            throw new IllegalArgumentException("Invalid ID " + id);
        }
    }

    public void deleteImageById(UUID id) {
        imageRepository.deleteById(id);
    }
}
